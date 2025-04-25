import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DtoGeneratorVer2 {

  private static final Set<String> requiredImports = new HashSet<>();

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
    requiredImports.clear();
    File javaFile = new File(outputDir + File.separator + dtoName + ".java");

    if (javaFile.exists()) {
      updateExistingClass(javaFile, dtoNode);
    } else {
      createNewClass(javaFile, dtoName, dtoNode);
    }
  }


  private static void createNewClass(File javaFile, String dtoName, JsonNode dtoNode)
      throws IOException {
    try (PrintWriter writer = new PrintWriter(javaFile)) {
      writer.println("package dto.generated;");
      writer.println();

      if (dtoNode.has("enum")) {
        generateEnumClass(writer, dtoName, dtoNode);
        return;
      }

      // Сначала генерируем поля, чтобы собрать все необходимые импорты
      String fields = generateFields(dtoNode);

      writer.println("import lombok.AllArgsConstructor;");
      writer.println("import lombok.Getter;");
      writer.println("import lombok.Setter;");

      if (checkIfNeedsArrayListImport(dtoNode)) {
        writer.println("import java.util.ArrayList;");
      }

      // Добавляем кастомные импорты
      for (String importClass : requiredImports) {
        writer.println("import " + importClass + ";");
      }

      writer.println();
      writer.println("@AllArgsConstructor");
      writer.println("@Getter");
      writer.println("@Setter");
      writer.println("public class " + dtoName + " {");
      writer.println();
      writer.print(fields);
      writer.println("}");
    }
  }


  private static void updateExistingClass(File javaFile, JsonNode dtoNode) throws IOException {
    String content = new String(Files.readAllBytes(javaFile.toPath()));
    Set<String> existingFields = getExistingFields(content);
    Set<String> newFields = getFieldsFromJson(dtoNode);

    if (existingFields.equals(newFields)) {
      System.out.println("No changes detected for " + javaFile.getName());
      return;
    }

    String updatedContent = updateClassContent(content, dtoNode);
    try (PrintWriter writer = new PrintWriter(javaFile)) {
      writer.print(updatedContent);
    }
  }

  private static String generateFields(JsonNode dtoNode) throws IOException {
    StringBuilder sb = new StringBuilder();
    if (dtoNode.has("properties")) {
      JsonNode properties = dtoNode.get("properties");
      Iterator<Map.Entry<String, JsonNode>> props = properties.fields();

      while (props.hasNext()) {
        Map.Entry<String, JsonNode> prop = props.next();
        String propName = prop.getKey();
        String propType = getJavaType(prop.getValue());

        sb.append("    private ").append(propType).append(" ")
            .append(propName).append(";\n");
      }
    }
    return sb.toString();
  }


  private static String updateClassContent(String originalContent, JsonNode dtoNode)
      throws IOException {
    int fieldsStart = originalContent.indexOf("@Setter") + "@Setter".length();
    fieldsStart = originalContent.indexOf("\n", fieldsStart) + 1;
    int fieldsEnd = originalContent.indexOf("}", fieldsStart);

    String newFields = generateFields(dtoNode);
    return originalContent.substring(0, fieldsStart) + newFields + originalContent.substring(
        fieldsEnd);
  }

  private static void generateEnumClass(PrintWriter writer, String enumName, JsonNode dtoNode) {
    writer.println("public enum " + enumName + " {");
    writer.println();

    JsonNode enumValues = dtoNode.get("enum");
    if (enumValues.isArray()) {
      for (int i = 0; i < enumValues.size(); i++) {
        String value = enumValues.get(i).asText();
        writer.print("    " + value.toUpperCase().replaceAll("-", "_"));
        if (i < enumValues.size() - 1) {
          writer.print(",");
        }
        writer.println();
      }
    }
    writer.println("}");
  }


  // Остальные методы без изменений
  private static Set<String> getExistingFields(String classContent) {
    Set<String> fields = new HashSet<>();
    Pattern pattern = Pattern.compile("private\\s+(\\w+<[^>]+>|\\w+)\\s+(\\w+);");
    Matcher matcher = pattern.matcher(classContent);

    while (matcher.find()) {
      fields.add(matcher.group(2));


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


  private static boolean checkIfNeedsArrayListImport(JsonNode dtoNode) {
    if (dtoNode.has("properties")) {
      JsonNode properties = dtoNode.get("properties");
      Iterator<Map.Entry<String, JsonNode>> props = properties.fields();
      while (props.hasNext()) {
        JsonNode propValue = props.next().getValue();

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
      String className = normalizeClassName(extractClassName(ref));
      if (!className.startsWith("java.")) {
        requiredImports.add(className);
      }
      return className;
    }

    if (propertyNode.has("type")) {
      String type = propertyNode.get("type").asText();
      switch (type) {
        case "string":
          if (propertyNode.has("format")) {
            String format = propertyNode.get("format").asText();
            if ("date-time".equals(format) || "date".equals(format)) {
              return "String";
            }
          }
          return "String";
        case "integer":
          return propertyNode.has("format") && "int64".equals(propertyNode.get("format").asText())
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

    return normalized.isEmpty() ? "UnknownType"
        : normalized.substring(0, 1).toUpperCase() + normalized.substring(1);
  }


}
