import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DtoGenerator {

  public static void main(String[] args) {
    String inputJson = "src/main/resources/swagger.json";
    String outputDir = "src/main/java/dto/generated";

    try {
      Path outputPath = Paths.get(outputDir);
      if (!Files.exists(outputPath)) {
        Files.createDirectories(outputPath);
      }

      generateDtoClasses(inputJson, outputDir);
      System.out.println("DTO classes generated successfully in: " + outputPath.toAbsolutePath());
    } catch (IOException e) {
      System.err.println("Error generating DTO classes: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void generateDtoClasses(String inputJson, String outputDir) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(new File(inputJson));

    if (rootNode.has("components") && rootNode.get("components").has("schemas")) {
      JsonNode schemas = rootNode.get("components").get("schemas");

      Iterator<Map.Entry<String, JsonNode>> fields = schemas.fields();
      while (fields.hasNext()) {
        Map.Entry<String, JsonNode> entry = fields.next();
        String originalDtoName = entry.getKey();
        String normalizedDtoName = normalizeClassName(originalDtoName);

        generateJavaClass(normalizedDtoName, entry.getValue(), outputDir);
      }
    }
  }

  private static void generateJavaClass(String dtoName, JsonNode dtoNode, String outputDir)
      throws IOException {
    File javaFile = new File(outputDir + File.separator + dtoName + ".java");

    if (javaFile.exists()) {
      updateExistingClass(javaFile, dtoNode);
    } else {
      createNewClass(javaFile, dtoName, dtoNode);
    }
  }

  private static void createNewClass(File javaFile, String dtoName, JsonNode dtoNode)
      throws IOException {
    try (FileWriter writer = new FileWriter(javaFile)) {
      writer.write("package dto.generated;\n\n");

      if (dtoNode.has("enum")) {
        generateEnumClass(writer, dtoName, dtoNode);
        return;
      }

      writer.write("import lombok.AllArgsConstructor;\n");
      writer.write("import lombok.Getter;\n");
      writer.write("import lombok.Setter;\n");

      if (checkIfNeedsArrayListImport(dtoNode)) {
        writer.write("import java.util.ArrayList;\n");
      }
      writer.write("\n");

      writer.write("@AllArgsConstructor\n");
      writer.write("@Getter\n");
      writer.write("@Setter\n");
      writer.write("public class " + dtoName + " {\n\n");

      generateFields(writer, dtoNode);

      writer.write("}\n");
    }
  }

  private static void updateExistingClass(File javaFile, JsonNode dtoNode) throws IOException {
    String content = new String(Files.readAllBytes(javaFile.toPath()));

    // Получаем существующие поля из класса
    Set<String> existingFields = getExistingFields(content);

    // Получаем новые поля из JSON
    Set<String> newFields = getFieldsFromJson(dtoNode);

    // Если нет изменений в полях, ничего не делаем
    if (existingFields.equals(newFields)) {
      System.out.println("No changes detected for " + javaFile.getName());
      return;
    }

    // Обновляем содержимое файла
    String updatedContent = updateClassContent(content, dtoNode);

    try (FileWriter writer = new FileWriter(javaFile)) {
      writer.write(updatedContent);
    }
  }

  private static Set<String> getExistingFields(String classContent) {
    Set<String> fields = new HashSet<>();
    Pattern pattern = Pattern.compile("public\\s+(\\w+<[^>]+>|\\w+)\\s+(\\w+);");
    Matcher matcher = pattern.matcher(classContent);

    while (matcher.find()) {
      fields.add(matcher.group(2)); // Имя поля
    }
    return fields;
  }

  private static Set<String> getFieldsFromJson(JsonNode dtoNode) {
    Set<String> fields = new HashSet<>();
    if (dtoNode.has("properties")) {
      Iterator<Map.Entry<String, JsonNode>> props = dtoNode.get("properties").fields();
      while (props.hasNext()) {
        fields.add(props.next().getKey());
      }
    }
    return fields;
  }

  private static String updateClassContent(String originalContent, JsonNode dtoNode)
      throws IOException {
    // Находим позицию последней аннотации перед полями
    int fieldsStart = originalContent.indexOf("@Setter\n") + "@Setter\n".length();
    fieldsStart = originalContent.indexOf("\n", fieldsStart) + 1;

    // Находим позицию конца полей (первая закрывающая скобка после начала полей)
    int fieldsEnd = originalContent.indexOf("}", fieldsStart);

    // Генерируем новые поля
    StringBuilder newFieldsWriter = new StringBuilder();
    generateFields(newFieldsWriter, dtoNode);
    String newFields = newFieldsWriter.toString();

    // Собираем обновленное содержимое
    return originalContent.substring(0, fieldsStart) +
        newFields +
        originalContent.substring(fieldsEnd);
  }

  private static void generateFields(Appendable writer, JsonNode dtoNode) throws IOException {
    if (dtoNode.has("properties")) {
      JsonNode properties = dtoNode.get("properties");
      Iterator<Map.Entry<String, JsonNode>> props = properties.fields();

      while (props.hasNext()) {
        Map.Entry<String, JsonNode> prop = props.next();
        String propName = prop.getKey();
        String propType = getJavaType(prop.getValue());

        writer.append("    public ").append(propType).append(" ").append(propName).append(";\n");
      }
    }
  }

  private static void generateEnumClass(FileWriter writer, String enumName, JsonNode dtoNode)
      throws IOException {
    writer.write("public enum " + enumName + " {\n");

    JsonNode enumValues = dtoNode.get("enum");
    if (enumValues.isArray()) {
      for (int i = 0; i < enumValues.size(); i++) {
        String value = enumValues.get(i).asText();
        // Сохраняем оригинальное название значения enum
        writer.write("    " + value.toUpperCase().replaceAll("-", "_"));
        if (i < enumValues.size() - 1) {
          writer.write(",");
        }
        writer.write("\n");
      }
    }
    writer.write("}\n");
  }

  private static boolean checkIfNeedsArrayListImport(JsonNode dtoNode) {
    if (dtoNode.has("properties")) {
      JsonNode properties = dtoNode.get("properties");
      Iterator<Map.Entry<String, JsonNode>> props = properties.fields();
      while (props.hasNext()) {
        Map.Entry<String, JsonNode> prop = props.next();
        JsonNode propValue = prop.getValue();
        if (propValue.has("type") && "array".equals(propValue.get("type").asText())) {
          return true;
        }
      }
    }
    return false;
  }

  private static String getJavaType(JsonNode propertyNode) {
    if (propertyNode.has("$ref")) {
      String ref = propertyNode.get("$ref").asText();
      return normalizeClassName(extractClassName(ref));
    }

    if (propertyNode.has("type")) {
      String type = propertyNode.get("type").asText();
      switch (type) {
        case "string":
          if (propertyNode.has("format")) {
            String format = propertyNode.get("format").asText();
            if ("date-time".equals(format)) {
              return "String";
            } else if ("date".equals(format)) {
              return "String";
            }
          }
          if (propertyNode.has("enum")) {
            return "String"; // Для строковых enum
          }
          return "String";
        case "integer":
          return propertyNode.has("format") && propertyNode.get("format").asText().equals("int64")
              ? "Long" : "Integer";
        case "number":
          return "Double";
        case "boolean":
          return "Boolean";
        case "array":
          JsonNode items = propertyNode.get("items");
          String itemType = getJavaType(items);
          return "ArrayList<" + itemType + ">";
      }
    }
    return "Object";
  }

  private static String extractClassName(String ref) {
    String[] parts = ref.split("/");
    return parts[parts.length - 1];
  }

  private static String normalizeClassName(String originalName) {
    if (originalName == null || originalName.isEmpty()) {
      return "UnknownType";
    }

    String normalized = originalName.replaceAll("[^a-zA-Z0-9_$]", "");
    normalized = normalized.replaceAll("_+", "_");
    normalized = normalized.replaceAll("^_|_$", "");

    if (normalized.matches("^[0-9].*")) {
      normalized = "C" + normalized;
    }

    if (normalized.isEmpty()) {
      return "UnknownType";
    }

    return normalized.substring(0, 1).toUpperCase() + normalized.substring(1);
  }
}
