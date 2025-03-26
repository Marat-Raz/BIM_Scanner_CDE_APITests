package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import groovyjarjarpicocli.CommandLine.Model;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.util.Map;

public class ModelRevisionsClient extends Client {

  private final String MODELS = "/models/";
  private final String REVISIONS = "/revisions/";

  @Step("Получить список моделей в проекте с параметрами запроса")
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

  @Step("Загрузить новый файл модели")
  public ValidatableResponse uploadNewModelFile(
      String projectId,
      String modelId,
      File modelFile,
      String comment
  ) {
    return given()
        .spec(multipartBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .multiPart("file", modelFile)  // Основной файл модели
        .multiPart("comment", comment)
        .when()
        .post(API_PROJECTS + projectId + MODELS + modelId + REVISIONS)
        .then();
  }

  @Step("Получить модель в проекте по ID")
  public ValidatableResponse getModelInProjectById(String projectId, String modelId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MODELS + modelId)
        .then();
  }

  @Step("Обновить модель в проекте по ID")
  public ValidatableResponse updateModelInProjectById(String projectId, String modelId,
      Model updatedModel) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedModel)
        .when()
        .put(API_PROJECTS + projectId + MODELS + modelId)
        .then();
  }

  @Step("Удалить модель в проекте по ID")
  public ValidatableResponse deleteModelInProjectById(String projectId, String modelId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + projectId + MODELS + modelId)
        .then();
  }
}