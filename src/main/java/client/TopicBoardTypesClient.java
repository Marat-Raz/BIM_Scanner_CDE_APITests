package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.types.Types;

public class TopicBoardTypesClient extends Client {

  private static final String TOPIC_BOARD_TYPES = "api/issues/boards/";

  @Step("Получить статусы, доступные на доске задач")
  public ValidatableResponse getTopicBoardTypes(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPIC_BOARD_TYPES + topicBoardId + "/types")
        .then();
  }

  @Step("Добавить тип задачи на доску задач")
  public ValidatableResponse addTypesToTopicBoard(String topicBoardId, Types type) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(type)
        .when()
        .post(TOPIC_BOARD_TYPES + topicBoardId + "/types")
        .then();
  }

  @Step("Редактировать тип задачи на доске задач")
  public ValidatableResponse editTypesInTopicBoard(String topicBoardId, String typeId,
      Types editedTypes) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(editedTypes)
        .when()
        .put(TOPIC_BOARD_TYPES + topicBoardId + "/types/" + typeId)
        .then();
  }

  @Step("Удалить тип задачи с доски задач")
  public ValidatableResponse deleteTypesInTopicBoard(String topicBoardId, String typeId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(TOPIC_BOARD_TYPES + topicBoardId + "/types/" + typeId)
        .then();
  }
}
