package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.customfields.CustomField;

public class CustomFieldsClient extends Client {

  private static final String CUSTOM_FIELDS = "api/projects/";

  @Step("Получить список групп досок и досок задач в корне проекта")
  public ValidatableResponse addNewCustomFieldToProject(String projectId, CustomField customField) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(customField)
        .when()
        .post(CUSTOM_FIELDS + projectId + "/custom-fields/")
        .then();
  }
}
