package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.priorities.Priorities;

public class TopicBoardPrioritiesClient extends Client {

  private static final String TOPIC_BOARD_PRIORITIES = "api/issues/boards/";

  @Step("Получить приоритеты, доступные на доске задач")
  public ValidatableResponse getTopicBoardPriorities(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPIC_BOARD_PRIORITIES + topicBoardId + "/priorities")
        .then();
  }

  @Step("Добавить приоритет на доску задач")
  public ValidatableResponse addPrioritiesToTopicBoard(String topicBoardId, Priorities priority) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(priority)
        .when()
        .post(TOPIC_BOARD_PRIORITIES + topicBoardId + "/priorities")
        .then();
  }

  @Step("Редактировать приоритет на доске задач")
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

  @Step("Удалить приоритет с доски задач")
  public ValidatableResponse deletePrioritiesInTopicBoard(String topicBoardId, String prioritiesId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(TOPIC_BOARD_PRIORITIES + topicBoardId + "/priorities/" + prioritiesId)
        .then();
  }
}
