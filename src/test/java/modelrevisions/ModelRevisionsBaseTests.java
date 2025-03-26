package modelrevisions;

import static dtomodels.models.ModelType.DEFAULT;

import basetests.RestAssuredLogging;
import basetests.StartTests;
import client.ModelRevisionsClient;
import client.ModelsClient;
import dtomodels.models.Model;
import dtomodels.models.ModelFileFormat;
import dtomodels.models.ModelsFactory;
import dtomodels.models.modelrevisions.ResponseModelRevisions;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class ModelRevisionsBaseTests extends StartTests {

  protected static ModelsClient modelsClient = new ModelsClient();
  protected static ModelRevisionsClient modelRevisionsClient = new ModelRevisionsClient();
  protected static ModelsFactory modelsFactory = new ModelsFactory();
  protected static ValidatableResponse createModelResponse;
  protected static ValidatableResponse uploadModelFileResponse;
  protected static ResponseModelRevisions responseModelRevisions;
  protected static String modelId;
  protected static String revisionId;
  protected static File modelFile =
      new File("src/main/resources/upload/Gladilova_AC_(IFC2x3).ifczip");

  @BeforeAll
  @Step("Создание модели и загрузка файла перед тестами")
  public static void setUpModel() {
    Model model = modelsFactory.createNameForModel(DEFAULT);
    createModelResponse = modelsClient.createModelInProject(projectId, model);
    modelId = createModelResponse.extract().path("id");
  }

  @AfterEach
  void restoreOriginalFilters() {
    RestAssuredLogging.restoreOriginalFilters();
  }

  @AfterAll
  @Step("Удаление модели после тестов")
  public static void cleanUpModel() {
    if (modelId != null) {
      modelsClient.deleteModelById(projectId, modelId);
    }
  }

  @Step("Скачивание и сохранение файла")
  public File downloadAndSaveModelRevisionFile(
      String projectId,
      String modelId,
      int version,
      ModelFileFormat format,
      String outputPath) throws IOException {

    byte[] fileBytes = modelRevisionsClient.downloadModelRevisionFile(projectId, modelId, version,
            format)
        .extract().asByteArray();

    File outputFile = new File(outputPath);
    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
      fos.write(fileBytes);
    }
    return outputFile;
  }

}