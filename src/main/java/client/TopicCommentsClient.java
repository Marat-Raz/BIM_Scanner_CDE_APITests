package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import dto.generated.CdeCreateTopicCommentDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class TopicCommentsClient extends Client {

  private final String TOPICS = "/topics/";
  private final String COMMENTS = "/comments/";

  @Step("Создать комментарий к задаче")
  public ValidatableResponse createTopicComment(String topicBoardId, String topicId, CdeCreateTopicCommentDto createTopicCommentDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(createTopicCommentDto)
        .when()
        .post(API_ISSUES_BOARDS + topicBoardId + TOPICS + topicId + COMMENTS)
        .then();
  }

  @Step("Получить список комментариев к задаче")
  public ValidatableResponse getTopicComments(String topicBoardId, String topicId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_ISSUES_BOARDS + topicBoardId + TOPICS + topicId + COMMENTS)
        .then();
  }

  @Step("Получить комментарий к задаче по ID")
  public ValidatableResponse getTopicCommentById(String topicBoardId, String topicId, String topicCommentId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_ISSUES_BOARDS + topicBoardId + TOPICS + topicId + COMMENTS + topicCommentId)
        .then();
  }

  @Step("Обновить комментарий к задаче по ID")
  public ValidatableResponse updateTopicComment(String topicBoardId, String topicId, String topicCommentId, CdeCreateTopicCommentDto updatedCreateTopicCommentDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedCreateTopicCommentDto)
        .when()
        .put(API_ISSUES_BOARDS + topicBoardId + TOPICS + topicId + COMMENTS + topicCommentId)
        .then();
  }

  @Step("Удалить комментарий к задаче по ID")
  public ValidatableResponse deleteTopicComment(String topicBoardId, String topicId, String topicCommentId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_ISSUES_BOARDS + topicBoardId + TOPICS + topicId + COMMENTS + topicCommentId)
        .then();
  }

}
/* //todo
Map<String, Object> queryParams = new HashMap<>();
queryParams.put("filter", "active");
queryParams.put("search", "urgent");
queryParams.put("orderBy", "createdAt");
queryParams.put("top", "10");
queryParams.put("skip", "0");

ValidatableResponse response = topicCommentsClient.getTopicComments(topicBoardId, topicId, queryParams);
 */
