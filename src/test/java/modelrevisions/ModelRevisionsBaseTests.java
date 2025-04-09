package modelrevisions;

import static dtomodels.models.ModelType.DEFAULT;

import basetests.RestAssuredLogging;
import basetests.StartTests;
import client.ModelRevisionsClient;
import client.ModelsClient;
import dtomodels.comment.Comment;
import dtomodels.models.Model;
import dtomodels.models.ModelFileFormat;
import dtomodels.models.ModelsFactory;
import dtomodels.models.modelrevisions.ResponseModelRevisions;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ModelRevisionsBaseTests extends StartTests {

  protected static ModelsClient modelsClient = new ModelsClient();
  protected static ModelRevisionsClient modelRevisionsClient = new ModelRevisionsClient();
  protected static ModelsFactory modelsFactory = new ModelsFactory();
  protected static ValidatableResponse createModelResponse;
  protected static ValidatableResponse uploadModelFileResponse;
  protected static ResponseModelRevisions responseModelRevisions;
  protected static String modelId;
  protected static List<ResponseModelRevisions> modelRevisionsArrayFromResponse = new ArrayList<>();
  protected static int modelRevisionCount = 3;
  private static File modelFile =
      new File("src/main/resources/upload/Gladilova_AC_(IFC2x3).ifc");
  private final String DEFAULT_DOWNLOAD_DIR = "src/main/resources/download/";
  private final String FILE_BASE_NAME = "Gladilova_AC_(IFC2x3)";

  @BeforeAll
  @Step("Создание модели и загрузка файлов перед тестами")
  public static void setUpModel() {
    Model model = modelsFactory.createNameForModel(DEFAULT);
    createModelResponse = modelsClient.createModelInProject(projectId, model);
    modelId = createModelResponse.extract().path("id");
    RestAssuredLogging.setupMinimalLogging();
    for (int i = 0; i < modelRevisionCount; i++) {
      uploadModelFileResponse = modelRevisionsClient
          .uploadNewModelFile(projectId, modelId, modelFile, new Comment("comment №" + i));
      responseModelRevisions = uploadModelFileResponse.extract().as(ResponseModelRevisions.class);
      modelRevisionsArrayFromResponse.add(responseModelRevisions);
    }
    RestAssuredLogging.restoreOriginalFilters();
  }

  @AfterAll
  @Step("Удаление модели после тестов")
  public static void cleanUpModel() {
    if (modelId != null) {
      modelsClient.deleteModelById(projectId, modelId);
    }
  }

  @Step("Сохранение файла") // todo вынести в Steps
  public File saveModelRevisionFile(
      byte[] content,
      int version,
      ModelFileFormat format) throws IOException {

    File outputFile = getDefaultDownloadPath(version, format);
    Files.createDirectories(outputFile.getParentFile().toPath());

    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
      fos.write(content);
    }
    return outputFile;
  }

  private String getFileExtension(ModelFileFormat format) { // todo вынести в Steps
    return switch (format) {
      case IFC -> ".ifc";
      case IFC_ZIP -> ".ifczip";
      case GLTF_BINARY -> ".gbin";
      case IFC_XML -> ".ifcXML";
      case XKT -> ".xkt";
    };
  }

  protected File getDefaultDownloadPath(int version, ModelFileFormat format) { // todo вынести в Steps
    String fileName = String.format("%s-v%d%s",
        FILE_BASE_NAME,
        version,
        getFileExtension(format));
    return new File(DEFAULT_DOWNLOAD_DIR, fileName);
  }

}