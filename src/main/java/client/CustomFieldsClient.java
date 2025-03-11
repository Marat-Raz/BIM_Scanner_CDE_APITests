package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import models.customfields.CustomField;
import models.customfields.CustomFieldToEdit;

public class CustomFieldsClient extends Client {

  private static final String CUSTOM_FIELDS = "api/projects/";

  @Step("Получить список кастомных полей проекта")
  public ValidatableResponse getCustomFields(String projectId, Map<String, String> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(CUSTOM_FIELDS + projectId + "/custom-fields/")
        .then();
  }

  @Step("Создать кастомное поле в проекте")
  public ValidatableResponse addNewCustomFieldToProject(String projectId, CustomField customField) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(customField)
        .when()
        .post(CUSTOM_FIELDS + projectId + "/custom-fields/")
        .then();
  }

  @Step("Получить кастомное поле по ID")
  public ValidatableResponse getCustomFieldById(String projectId, String customFieldId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(CUSTOM_FIELDS + projectId + "/custom-fields/" + customFieldId)
        .then();
  }

  @Step("Обновить кастомное поле по ID")
  public ValidatableResponse editCustomField(String projectId, String customFieldId,
      CustomFieldToEdit customFieldToEdit) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(customFieldToEdit)
        .when()
        .patch(CUSTOM_FIELDS + projectId + "/custom-fields/" + customFieldId)
        .then();
  }

  @Step("Получить элементы перечисления для кастомного поля")
  public ValidatableResponse getCustomFieldEnumerationItems(String projectId,
      String customFieldId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(CUSTOM_FIELDS + projectId + "/custom-fields/" + customFieldId + "/enumeration-items")
        .then();
  }
}

