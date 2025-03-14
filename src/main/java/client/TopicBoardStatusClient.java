package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.statuses.Statuses;

public class TopicBoardStatusClient extends Client {

  private static final String STATUSES = "/statuses/";

  @Step("Получить статусы, доступные на доске задач")
  public ValidatableResponse getTopicBoardStatuses(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_ISSUES_BOARDS + topicBoardId + STATUSES)
        .then();
  }

  @Step("Добавить статус на доску задач")
  public ValidatableResponse addTopicBoardStatuses(String topicBoardId, Statuses status) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(status)
        .when()
        .post(API_ISSUES_BOARDS + topicBoardId + STATUSES)
        .then();
  }

  @Step("Редактировать статус на доске задач")
  public ValidatableResponse editTopicBoardStatuses(String topicBoardId, String statusId,
      Statuses editedStatus) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(editedStatus)
        .when()
        .put(API_ISSUES_BOARDS + topicBoardId + STATUSES + statusId)
        .then();
  }

  @Step("Удалить статус с доски задач")
  public ValidatableResponse deleteTopicBoardStatuses(String topicBoardId, String statusId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_ISSUES_BOARDS + topicBoardId + STATUSES + statusId)
        .then();
  }
}
