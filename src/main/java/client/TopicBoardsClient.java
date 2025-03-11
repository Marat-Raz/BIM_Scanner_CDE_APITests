package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.customfields.customfieldsintopicbords.CustomFieldsOnTopicBoards;
import models.topicboards.TopicBoards;

public class TopicBoardsClient extends Client {

  private static final String TOPIC_BOARDS = "api/projects/";

  @Step("Получить доску задач по id")
  public ValidatableResponse getTopicBoard(String projectId, String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPIC_BOARDS + projectId + "/issues/boards/" + topicBoardId)
        .then();
  }

  @Step("Создать доску задач в проекте")
  public ValidatableResponse createNewTopicBoard(String projectId,
      TopicBoards topicBoard) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(topicBoard)
        .when()
        .post(TOPIC_BOARDS + projectId + "/issues/boards/")
        .then();
  }

  @Step("Изменить доску задач")
  public ValidatableResponse updateTopicBoard(String projectId,
      String topicBoardId, TopicBoards topicBoard) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(topicBoard)
        .when()
        .put(TOPIC_BOARDS + projectId + "/issues/boards/" + topicBoardId)
        .then();
  }

  @Step("Редактировать кастомные поля доски задач")
  public ValidatableResponse editTopicBoardCustomFields(String projectId,
      String topicBoardId, CustomFieldsOnTopicBoards customFieldsOnTopicBoards) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(customFieldsOnTopicBoards)
        .when()
        .patch(TOPIC_BOARDS + projectId + "/issues/boards/" + topicBoardId)
        .then();
  }

  @Step("Удалить доску задач")
  public ValidatableResponse deleteTopicBoard(String projectId,
      String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(TOPIC_BOARDS + projectId + "/issues/boards/" + topicBoardId)
        .then();
  }

}
