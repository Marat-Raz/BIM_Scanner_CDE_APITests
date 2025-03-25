package models;

import static dtomodels.models.ModelType.DEFAULT;

import basetests.StartTests;
import client.ModelsClient;
import dtomodels.PaginatedResponse;
import dtomodels.models.Model;
import dtomodels.models.ModelsFactory;
import dtomodels.models.ResponseModel;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ModelsBaseTests extends StartTests {

  protected static ModelsClient modelsClient = new ModelsClient();
  protected static ModelsFactory modelsFactory = new ModelsFactory();
  protected static TypeRef<PaginatedResponse<ResponseModel>> typeRef = new TypeRef<>() {};
  protected static ValidatableResponse createResponse;
  protected static ValidatableResponse getAllModelsResponse;
  protected static Model defaultModel;
  protected static ResponseModel defaultResponseModel;
  protected static String defaultModelId;

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
    PaginatedResponse<ResponseModel> paginatedResponse = getAllModelsResponse
        .extract().body().as(typeRef);
    for (ResponseModel model : paginatedResponse.getItems()) {
      modelsClient.deleteModelById(projectId, model.getId());
    }
  }
}
