package models;

import static dtomodels.models.ModelType.DEFAULT;

import basetests.StartTests;
import client.ModelsClient;
import dtomodels.models.ListOfModel;
import dtomodels.models.Model;
import dtomodels.models.ModelsFactory;
import dtomodels.models.ResponseModel;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ModelsBaseTests extends StartTests {

  protected static ModelsClient modelsClient = new ModelsClient();
  protected static ModelsFactory modelsFactory = new ModelsFactory();
  protected static ValidatableResponse createResponse;
  protected static ValidatableResponse getAllModelsResponse;
  protected static ValidatableResponse deleteResponse;
  protected static Model defaultModel;
  protected static ResponseModel defaultResponseModel;
  protected static String defaultModelId;
  protected static ListOfModel listOfModel;
  protected static List<ResponseModel> responseModelList;

  @BeforeAll
  public static void createModel() {
    defaultModel = modelsFactory.createNameForModel(DEFAULT);
    createResponse = modelsClient.createModelInProject(projectId, defaultModel);
    defaultResponseModel = createResponse.extract().as(ResponseModel.class);
    defaultModelId = defaultResponseModel.getId();
  }

  @AfterAll
  public static void deleteAllModelsFromProject() {
    getAllModelsResponse = modelsClient.getListOfModelsInProjectWithoutQueryOptions(projectId);
    listOfModel = getAllModelsResponse.extract().as(ListOfModel.class);
    responseModelList = listOfModel.getItems();
    for(ResponseModel model : responseModelList) {
      modelsClient.deleteModelById(projectId, model.getId());
    }
  }
}
