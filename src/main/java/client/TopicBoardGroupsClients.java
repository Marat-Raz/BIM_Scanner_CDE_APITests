package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.topicboardsgroup.TopicBoardsGroup;

public class TopicBoardGroupsClients extends Client {
  private static final String TOPIC_BOARD_GROUPS = "api/projects/";

  @Step("Получить список групп досок и досок задач в корне проекта")
  public ValidatableResponse getRootTopicBoardGroupsAndBoards(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPIC_BOARD_GROUPS + projectId + "/issues/board-groups/")
        .then();
  }

  @Step("Создать группу досок задач в корне проекта")
  public ValidatableResponse createNewTopicBoardsGroup(String projectId,
      TopicBoardsGroup topicBoardsGroup) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(topicBoardsGroup)
        .when()
        .post(TOPIC_BOARD_GROUPS + projectId + "/issues/board-groups/")
        .then();
  }

  @Step("Получить группу досок задач по его id, включая или нет содержимое")
  public ValidatableResponse getTopicBoardGroup(String projectId,
      String topicBoardGroupId, boolean includeChildren) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(includeChildren)
        .when()
        .get(TOPIC_BOARD_GROUPS + projectId + "/issues/board-groups/")
        .then();
  }

  @Step("Изменить группу досок задач")
  public ValidatableResponse updateTopicBoardGroup(String projectId,
      String groupId, TopicBoardsGroup topicBoardsGroup) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(topicBoardsGroup)
        .when()
        .put(TOPIC_BOARD_GROUPS + projectId + "/issues/board-groups/" + groupId)
        .then();
  }

  @Step("Удалить группу досок задач")
  public ValidatableResponse deleteTopicBoardGroup(String projectId,
      String groupId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(TOPIC_BOARD_GROUPS + projectId + "/issues/board-groups/" + groupId)
        .then();
  }

}
