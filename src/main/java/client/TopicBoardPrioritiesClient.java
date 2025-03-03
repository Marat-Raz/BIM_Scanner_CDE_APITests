package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.priorities.Priorities;

public class TopicBoardPrioritiesClient extends Client {

  private static final String TOPIC_BOARD_PRIORITIES = "api/issues/boards/";

  @Step("Получить статусы, доступные на доске задач")
  public ValidatableResponse getTopicBoardPriorities(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPIC_BOARD_PRIORITIES + topicBoardId + "/priorities")
        .then();
  }

  @Step("Добавить статус на доску задач")
  public ValidatableResponse addPrioritiesToTopicBoard(String topicBoardId, Priorities priorities) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(priorities)
        .when()
        .post(TOPIC_BOARD_PRIORITIES + topicBoardId + "/priorities")
        .then();
  }

  @Step("Редактировать статус на доске задач")
  public ValidatableResponse editPrioritiesInTopicBoard(String topicBoardId, String prioritiesId,
      Priorities editedPriorities) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(editedPriorities)
        .when()
        .put(TOPIC_BOARD_PRIORITIES + topicBoardId + "/priorities/" + prioritiesId)
        .then();
  }

  @Step("Удалить статус на доске задач")
  public ValidatableResponse deletePrioritiesInTopicBoard(String topicBoardId, String prioritiesId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(TOPIC_BOARD_PRIORITIES + topicBoardId + "/priorities/" + prioritiesId)
        .then();
  }
}
