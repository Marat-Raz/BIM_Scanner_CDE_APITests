import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


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

      Iterator<Entry<String, JsonNode>> fields = schemas.fields();
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
    try (FileWriter writer = new FileWriter(javaFile)) {

      writer.write("package generated.dto;\n\n");

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

      if (dtoNode.has("properties")) {
        JsonNode properties = dtoNode.get("properties");
        Iterator<Map.Entry<String, JsonNode>> props = properties.fields();

        while (props.hasNext()) {
          Map.Entry<String, JsonNode> prop = props.next();
          String propName = prop.getKey();
          String propType = getJavaType(prop.getValue());

          writer.write("    private " + propType + " " + propName + ";\n");
        }
      }
      writer.write("}\n");
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
