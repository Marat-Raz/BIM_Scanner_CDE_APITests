package client.base;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.statuses.Statuses;

public class TopicBoardStatusClient extends Client {

  private static final String TOPIC_BOARD_STATUS = "api/issues/boards/";

  @Step("Получить статусы, доступные на доске задач")
  public ValidatableResponse getTopicBoardStatuses(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPIC_BOARD_STATUS + topicBoardId + "/statuses")
        .then();
  }

  @Step("Добавить статус на доску задач")
  public ValidatableResponse addStatusToTopicBoard(String topicBoardId, Statuses status) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(status)
        .when()
        .post(TOPIC_BOARD_STATUS + topicBoardId + "/statuses")
        .then();
  }

  @Step("Редактировать статус на доске задач")
  public ValidatableResponse editStatusInTopicBoard(String topicBoardId, String statusId,
      Statuses editedStatus) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(editedStatus)
        .when()
        .put(TOPIC_BOARD_STATUS + topicBoardId + "/statuses/" + statusId)
        .then();
  }

  @Step("Удалить статус на доске задач")
  public ValidatableResponse deleteStatusInTopicBoard(String topicBoardId, String statusId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(TOPIC_BOARD_STATUS + topicBoardId + "/statuses/" + statusId)
        .then();
  }
}
