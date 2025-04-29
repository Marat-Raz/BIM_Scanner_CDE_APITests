import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DtoGenerator {

  private static final Set<String> imports = new HashSet<>();
  private static final Set<String> customTypes = new HashSet<>();
  private static JsonObject rootSchemas;

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
    JsonElement rootElement = JsonParser.parseReader(new FileReader(inputJson));
    JsonObject rootObject = rootElement.getAsJsonObject();

    if (rootObject.has("components") && rootObject.get("components").getAsJsonObject()
        .has("schemas")) {
      rootSchemas = rootObject.get("components").getAsJsonObject().get("schemas").getAsJsonObject();

      for (Map.Entry<String, JsonElement> entry : rootSchemas.entrySet()) {
        String dtoName = entry.getKey();
        String normalizedDtoName = normalizeClassName(dtoName);
        generateJavaClass(normalizedDtoName, entry.getValue().getAsJsonObject(), outputDir);
      }
    }
  }

  private static void generateJavaClass(String dtoName, JsonObject dtoNode, String outputDir)
      throws IOException {
    imports.clear();
    customTypes.clear();

    File javaFile = new File(outputDir + File.separator + dtoName + ".java");

    createNewClass(javaFile, dtoName, dtoNode);
  }

  private static void createNewClass(File javaFile, String dtoName, JsonObject dtoNode)
      throws IOException {
    try (PrintWriter writer = new PrintWriter(javaFile)) {
      Map<String, String> allFields = collectAllFields(dtoNode);

      writer.println("package dto.generated;\n");

      if (dtoNode.has("enum")) {
        generateEnumClass(writer, dtoName, dtoNode);
        return;
      }

      writer.println("import lombok.AllArgsConstructor;");
      writer.println("import lombok.EqualsAndHashCode;");
      writer.println("import lombok.Getter;");
      writer.println("import lombok.Setter;");

      if (allFields.values().stream().anyMatch(t -> t.contains("List"))) {
        writer.println("import java.util.ArrayList;");
      }
      if (allFields.values().stream().anyMatch(t -> t.contains("Map"))) {
        writer.println("import java.util.Map;");
      }

      for (String type : customTypes) {
        if (!type.equals(dtoName)) {
          writer.println("import dto.generated." + type + ";");
        }
      }

      writer.println();
      writer.println("@AllArgsConstructor");
      writer.println("@Getter");
      writer.println("@Setter");
      writer.println("@EqualsAndHashCode");
      writer.println("public class " + dtoName + " {");
      writer.println();

      for (Map.Entry<String, String> entry : allFields.entrySet()) {
        writer.println("    public " + entry.getValue() + " " + entry.getKey() + ";");
      }

      writer.println("}");
    }
  }

  private static Map<String, String> collectAllFields(JsonObject dtoNode) {
    Map<String, String> allFields = new LinkedHashMap<>();

    if (dtoNode.has("allOf")) {
      for (JsonElement parent : dtoNode.get("allOf").getAsJsonArray()) {
        if (parent.isJsonObject() && parent.getAsJsonObject().has("$ref")) {
          String ref = parent.getAsJsonObject().get("$ref").getAsString();
          String parentName = extractClassName(ref);
          JsonElement parentSchema = rootSchemas.get(parentName);
          if (parentSchema != null) {
            allFields.putAll(collectAllFields(parentSchema.getAsJsonObject()));
          }
        } else if (parent.isJsonObject() && parent.getAsJsonObject().has("properties")) {
          collectFields(parent.getAsJsonObject(), allFields);
        }
      }
    }

    if (dtoNode.has("properties")) {
      collectFields(dtoNode, allFields);
    }

    // Исключаем поле extraProperties
    allFields.remove("extraProperties");

    return allFields;
  }

  private static void collectFields(JsonObject node, Map<String, String> fields) {
    JsonObject properties = node.get("properties").getAsJsonObject();
    for (Map.Entry<String, JsonElement> prop : properties.entrySet()) {
      String fieldName = prop.getKey();
      // Пропускаем extraProperties
      if (!"extraProperties".equals(fieldName)) {
        fields.put(fieldName, getJavaType(prop.getValue().getAsJsonObject()));
      }
    }
  }

  private static void generateEnumClass(PrintWriter writer, String enumName, JsonObject dtoNode) {
    writer.println("public enum " + enumName + " {");
    writer.println();

    if (dtoNode.has("enum")) {
      JsonArray enumValues = dtoNode.get("enum").getAsJsonArray();
      for (int i = 0; i < enumValues.size(); i++) {
        String value = enumValues.get(i).getAsString();
        writer.print("    " + value.toUpperCase().replaceAll("-", "_"));
        if (i < enumValues.size() - 1) {
          writer.print(",");
        }
        writer.println();
      }
    }
    writer.println("}");
  }

  private static String getJavaType(JsonObject propertyNode) {
    if (propertyNode.has("$ref")) {
      String ref = propertyNode.get("$ref").getAsString();
      String className = normalizeClassName(extractClassName(ref));
      customTypes.add(className);
      return className;
    }

    if (propertyNode.has("type")) {
      String type = propertyNode.get("type").getAsString();
      switch (type) {
        case "string":
          return "String";
        case "integer":
          return
              propertyNode.has("format") && "int64".equals(propertyNode.get("format").getAsString())
                  ? "Long" : "Integer";
        case "number":
          return "Double";
        case "boolean":
          return "Boolean";
        case "array":
          JsonObject items = propertyNode.get("items").getAsJsonObject();
          String itemType = getJavaType(items);
          imports.add("java.util.ArrayList");
          return "ArrayList<" + itemType + ">";
        case "object":
          if (propertyNode.has("additionalProperties")) {
            imports.add("java.util.Map");
            JsonElement additionalProps = propertyNode.get("additionalProperties");
            String valueType = getJavaType(additionalProps.getAsJsonObject());
            return "Map<String, " + valueType + ">";
          }
          return "Object";
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

    String normalized = originalName.replaceAll("[^a-zA-Z0-9]", "_");
    normalized = normalized.replaceAll("_+", "_");
    normalized = normalized.replaceAll("^_|_$", "");

    if (normalized.matches("^[0-9].*")) {
      normalized = "C" + normalized;
    }

    return normalized.isEmpty() ? "UnknownType" : normalized;
  }
}
