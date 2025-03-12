package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import models.labels.Label;

public class LabelsClient extends Client {

  private final String LABELS = "/labels/";

  @Step("Создать метку в проекте")
  public ValidatableResponse createLabelInProject(String projectId, Label label) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(label)
        .when()
        .post(API_PROJECTS + projectId + LABELS)
        .then();
  }

  @Step("Получить список меток по id проекта без QueryOptions")
  public ValidatableResponse getListOfLabelInProjectWithoutQueryOptions(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + LABELS)
        .then();
  }

  @Step("Получить список меток по id проекта")
  public ValidatableResponse getListOfLabelInProject(String projectId,
      Map<String, Boolean> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + projectId + LABELS)
        .then();
  }

  @Step("Получить метку id и по id проекта")
  public ValidatableResponse getLabelInProject(String projectId, String labelId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + LABELS + labelId)
        .then();
  }

  @Step("Обновить существующую метку")
  public ValidatableResponse updateLabelInProject(String projectId, String labelId, Label updatedLabel) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedLabel)
        .when()
        .put(API_PROJECTS + projectId + LABELS + labelId)
        .then();
  }

  @Step("Удалить метку")
  public ValidatableResponse deleteLabelInProject(String projectId, String labelId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + projectId + LABELS + labelId)
        .then();
  }
}