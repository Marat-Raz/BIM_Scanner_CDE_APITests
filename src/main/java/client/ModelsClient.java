package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import dtomodels.models.Model;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;

public class ModelsClient extends Client {

  private final String MODELS = "/models/";

  @Step("Получить список моделей в проекте")
  public ValidatableResponse getListOfModelsInProject(String projectId,
      Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + projectId + MODELS)
        .then();
  }

  @Step("Получить список моделей в проекте без параметров запроса")
  public ValidatableResponse getListOfModelsInProjectWithoutQueryOptions(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MODELS)
        .then();
  }

  @Step("Создать модель в проекте")
  public ValidatableResponse createModelInProject(String projectId, Model model) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(model)
        .when()
        .post(API_PROJECTS + projectId + MODELS)
        .then();
  }

  @Step("Получить модель по ID")
  public ValidatableResponse getModelById(String projectId, String modelId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MODELS + modelId)
        .then();
  }

  @Step("Обновить модель по ID")
  public ValidatableResponse updateModelById(String projectId, String modelId,
      Model updatedModel) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedModel)
        .when()
        .put(API_PROJECTS + projectId + MODELS + modelId)
        .then();
  }

  @Step("Удалить модель по ID")
  public ValidatableResponse deleteModelById(String projectId, String modelId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + projectId + MODELS + modelId)
        .then();
  }
}