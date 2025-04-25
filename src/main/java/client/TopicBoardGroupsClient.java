package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeCreateTopicBoardGroupDto;

public class TopicBoardGroupsClient extends Client {

  public static final String BOARD_GROUPS = "/issues/board-groups/";

  @Step("Получить список групп досок и досок задач в корне проекта")
  public ValidatableResponse getRootTopicBoardGroupsAndBoards(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + BOARD_GROUPS)
        .then();
  }

  @Step("Создать группу досок задач в проекте")
  public ValidatableResponse createNewTopicBoardsGroup(String projectId,
      CdeCreateTopicBoardGroupDto cdeCreateTopicBoardGroupDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(cdeCreateTopicBoardGroupDto)
        .when()
        .post(API_PROJECTS + projectId + BOARD_GROUPS)
        .then();
  }

  @Step("Получить группу досок задач по его id, включая или нет содержимое")
  public ValidatableResponse getTopicBoardGroupById(String projectId,
      String topicBoardGroupId, boolean includeChildren) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(includeChildren)
        .when()
        .get(API_PROJECTS + projectId + BOARD_GROUPS)
        .then();
  }

  @Step("Изменить группу досок задач")
  public ValidatableResponse updateTopicBoardGroup(String projectId,
      String groupId, CdeCreateTopicBoardGroupDto cdeCreateTopicBoardGroupDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(cdeCreateTopicBoardGroupDto)
        .when()
        .put(API_PROJECTS + projectId + BOARD_GROUPS + groupId)
        .then();
  }

  @Step("Удалить группу досок задач")
  public ValidatableResponse deleteTopicBoardGroup(String projectId,
      String groupId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + projectId + BOARD_GROUPS + groupId)
        .then();
  }

}
