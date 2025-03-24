package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dtomodels.priorities.Priorities;

public class TopicBoardPrioritiesClient extends Client {

  private static final String PRIORITIES = "/priorities/";

  @Step("Получить приоритеты, доступные на доске задач")
  public ValidatableResponse getTopicBoardPriorities(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_ISSUES_BOARDS + topicBoardId + PRIORITIES)
        .then();
  }

  @Step("Добавить приоритет на доску задач")
  public ValidatableResponse addTopicBoardPriorities(String topicBoardId, Priorities priority) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(priority)
        .when()
        .post(API_ISSUES_BOARDS + topicBoardId + PRIORITIES)
        .then();
  }

  @Step("Редактировать приоритет на доске задач")
  public ValidatableResponse editTopicBoardPriorities(String topicBoardId, String prioritiesId,
      Priorities editedPriorities) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(editedPriorities)
        .when()
        .put(API_ISSUES_BOARDS + topicBoardId + PRIORITIES + prioritiesId)
        .then();
  }

  @Step("Удалить приоритет с доски задач")
  public ValidatableResponse deleteTopicBoardPriorities(String topicBoardId, String prioritiesId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_ISSUES_BOARDS + topicBoardId + PRIORITIES + prioritiesId)
        .then();
  }
}
