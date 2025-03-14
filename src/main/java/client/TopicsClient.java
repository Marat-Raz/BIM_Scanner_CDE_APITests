package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import models.topics.ResponseTopics;
import models.topics.Topics;

public class TopicsClient extends Client {

  private final String TOPICS = "/topics/";

  @Step("Получить список задач определенной доски задач")
  public ValidatableResponse getListOfTopicsFromTopicBoard(String topicBoardId,
      Map<String, Object> queryParams) {
    // todo реализовать тесты для этого метода
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_ISSUES_BOARDS + topicBoardId + TOPICS)
        .then();
  }

  @Step("Получить список задач определенной доски задач")
  public ValidatableResponse getListOfTopicsFromTopicBoardWithoutQueryOptions(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_ISSUES_BOARDS + topicBoardId + TOPICS)
        .then();
  }

  @Step("Создать задачу в доске задач")
  public ValidatableResponse createTopicOnTopicBoard(String topicBoardId, Topics topic) {
    // todo необходимо сформировать список опций/фильтров для этого запроса
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(topic)
        .when()
        .post(API_ISSUES_BOARDS + topicBoardId + TOPICS)
        .then();
  }

  @Step("Получить список задач в проекте")
  public ValidatableResponse getTopicsInProject(String projectId,
      Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + projectId + TOPICS)
        .then();
  }

  @Step("Получить список задач в проекте без QueryOptions")
  public ValidatableResponse getTopicsInProjectWithoutQueryOptions(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + TOPICS)
        .then();
  }

  @Step("Получить задачу в доске задач по ID")
  public ValidatableResponse getTopicOnTopicBoard(String topicBoardId, String topicId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_ISSUES_BOARDS + topicBoardId + TOPICS + topicId)
        .then();
  }

  @Step("Обновить задачу в доске задач по ID")
  public ValidatableResponse updateTopicOnTopicBoard(String topicBoardId,
      String topicId, ResponseTopics updatedTopic) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedTopic)
        .when()
        .put(API_ISSUES_BOARDS + topicBoardId + TOPICS + topicId)
        .then();
  }

  @Step("Удалить задачу в доске задач по ID")
  public ValidatableResponse deleteTopicOnTopicBoard(String topicBoardId, String topicId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_ISSUES_BOARDS + topicBoardId + TOPICS + topicId)
        .then();
  }

}
