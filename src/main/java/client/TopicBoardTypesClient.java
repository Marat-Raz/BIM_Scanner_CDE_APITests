package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.types.Types;

public class TopicBoardTypesClient extends Client {

  private static final String TYPES = "/types/";

  @Step("Получить статусы, доступные на доске задач")
  public ValidatableResponse getTopicBoardTypes(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_ISSUES_BOARDS + topicBoardId + TYPES)
        .then();
  }

  @Step("Добавить тип задачи на доску задач")
  public ValidatableResponse addTopicBoardTypes(String topicBoardId, Types type) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(type)
        .when()
        .post(API_ISSUES_BOARDS + topicBoardId + TYPES)
        .then();
  }

  @Step("Редактировать тип задачи на доске задач")
  public ValidatableResponse editTopicBoardTypes(String topicBoardId, String typeId,
      Types editedTypes) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(editedTypes)
        .when()
        .put(API_ISSUES_BOARDS + topicBoardId + TYPES + typeId)
        .then();
  }

  @Step("Удалить тип задачи с доски задач")
  public ValidatableResponse deleteTopicBoardTypes(String topicBoardId, String typeId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_ISSUES_BOARDS + topicBoardId + TYPES + typeId)
        .then();
  }
}
