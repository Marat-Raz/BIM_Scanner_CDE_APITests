package models;

import static dtomodels.models.ModelType.DEFAULT;

import basetests.StartTests;
import client.ModelsClient;
import dtomodels.PaginatedResponse;
import dto.generated.CdeCreateOrUpdateModelDto;
import dtomodels.models.ModelsFactory;
import dto.generated.CdeModelDto;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ModelsBaseTests extends StartTests {

  protected static ModelsClient modelsClient = new ModelsClient();
  protected static ModelsFactory modelsFactory = new ModelsFactory();
  protected static TypeRef<PaginatedResponse<CdeModelDto>> typeRef = new TypeRef<>() {};
  protected static ValidatableResponse createResponse;
  protected static ValidatableResponse getAllModelsResponse;
  protected static CdeCreateOrUpdateModelDto defaultCdeCreateOrUpdateModelDto;
  protected static CdeModelDto defaultCdeModelDto;
  protected static String defaultModelId;

  @BeforeAll
  public static void createModel() {
    defaultCdeCreateOrUpdateModelDto = modelsFactory.createNameForModel(DEFAULT);
    createResponse = modelsClient.createModelInProject(projectId, defaultCdeCreateOrUpdateModelDto);
    defaultCdeModelDto = createResponse.extract().as(CdeModelDto.class);
    defaultModelId = defaultCdeModelDto.getId();
  }

  @AfterAll
  public static void deleteAllModelsFromProject() {
    getAllModelsResponse = modelsClient.getListOfModelsInProjectWithoutQueryOptions(projectId);
    PaginatedResponse<CdeModelDto> paginatedResponse = getAllModelsResponse
        .extract().body().as(typeRef);
    for (CdeModelDto model : paginatedResponse.getItems()) {
      modelsClient.deleteModelById(projectId, model.getId());
    }
  }
}
