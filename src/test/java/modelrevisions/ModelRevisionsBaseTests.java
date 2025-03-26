package modelrevisions;

import static dtomodels.models.ModelType.DEFAULT;

import basetests.StartTests;
import client.ModelRevisionsClient;
import client.ModelsClient;
import dtomodels.modelrevisions.ResponseModelRevisions;
import dtomodels.models.Model;
import dtomodels.models.ModelsFactory;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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

  @AfterAll
  @Step("Удаление модели после тестов")
  public static void cleanUpModel() {
    if (modelId != null) {
      modelsClient.deleteModelById(projectId, modelId);
    }
  }

}