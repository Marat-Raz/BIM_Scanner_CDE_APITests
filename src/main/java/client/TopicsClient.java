package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import models.topics.ResponseTopics;
import models.topics.Topics;

public class TopicsClient extends Client {

  private final String TOPICS = "api/issues/boards/";

  @Step("Получить список задач определенной доски задач")
  public ValidatableResponse getListOfTopicsFromTopicBoard(String topicBoardId,
      Map<String, Object> queryParams) {
    // todo реализовать тесты для этого метода
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(TOPICS + topicBoardId + "/topics")
        .then();
  }

  @Step("Получить список задач определенной доски задач")
  public ValidatableResponse getListOfTopicsFromTopicBoardWithoutQueryOptions(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPICS + topicBoardId + "/topics")
        .then();
  }


  @Step("Создать задачу в доске задач")
  public ValidatableResponse createTopicInTopicBoard(String topicBoardId, Topics topic) {
    // todo необходимо сформировать список опций/фильтров для этого запроса
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(topic)
        .when()
        .post(TOPICS + topicBoardId + "/topics")
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
        .get("api/projects/" + projectId + "/topics")
        // todo вывести  "api/projects/" в константы
        .then();
  }

  @Step("Получить список задач в проекте без QueryOptions")
  public ValidatableResponse getTopicsInProjectWithoutQueryOptions(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get("api/projects/" + projectId + "/topics")
        .then();
  }

  @Step("Получить задачу в доске задач по ID")
  public ValidatableResponse getTopicInTopicBoard(String topicBoardId, String topicId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPICS + topicBoardId + "/topics/" + topicId)
        .then();
  }

  @Step("Обновить задачу в доске задач по ID")
  public ValidatableResponse updateTopicInTopicBoard(String topicBoardId,
      String topicId, ResponseTopics updatedTopic) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedTopic)
        .when()
        .put(TOPICS + topicBoardId + "/topics/" + topicId)
        .then();
  }

  @Step("Удалить задачу в доске задач по ID")
  public ValidatableResponse deleteTopicInTopicBoard(String topicBoardId, String topicId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(TOPICS + topicBoardId + "/topics/" + topicId)
        .then();
  }

}
