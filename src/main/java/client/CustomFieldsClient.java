package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import dto.generated.CdeUpdateCustomFieldDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeCreateCustomFieldDto;
import java.util.Map;


public class CustomFieldsClient extends Client {
  public static final String CUSTOM_FIELDS = "/custom-fields/";

  @Step("Получить список кастомных полей проекта")
  public ValidatableResponse getCustomFields(String projectId, Map<String, String> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + projectId + CUSTOM_FIELDS)
        .then();
  }

  @Step("Создать кастомное поле в проекте")
  public ValidatableResponse addNewCustomFieldToProject(String projectId, CdeCreateCustomFieldDto cdeCreateCustomFieldDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(cdeCreateCustomFieldDto)
        .when()
        .post(API_PROJECTS + projectId + CUSTOM_FIELDS)
        .then();
  }

  @Step("Получить кастомное поле по ID")
  public ValidatableResponse getCustomFieldById(String projectId, String customFieldId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + CUSTOM_FIELDS + customFieldId)
        .then();
  }

  @Step("Обновить кастомное поле по ID")
  public ValidatableResponse editCustomField(String projectId, String customFieldId,
      CdeUpdateCustomFieldDto cdeUpdateCustomFieldDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(cdeUpdateCustomFieldDto)
        .when()
        .patch(API_PROJECTS + projectId + CUSTOM_FIELDS + customFieldId)
        .then();
  }

  @Step("Получить элементы перечисления для кастомного поля")
  public ValidatableResponse getCustomFieldEnumerationItems(String projectId,
      String customFieldId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + CUSTOM_FIELDS + customFieldId + "/enumeration-items")
        .then();
  }
}

