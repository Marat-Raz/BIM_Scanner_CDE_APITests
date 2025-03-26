package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dtomodels.comment.Comment;

public class TopicCommentsClient extends Client {

  private final String TOPICS = "/topics/";
  private final String COMMENTS = "/comments/";

  @Step("Создать комментарий к задаче")
  public ValidatableResponse createTopicComment(String topicBoardId, String topicId, Comment comment) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(comment)
        .when()
        .post(API_PROJECTS + topicBoardId + TOPICS + topicId + COMMENTS)
        .then();
  }

  @Step("Получить список комментариев к задаче")
  public ValidatableResponse getTopicComments(String topicBoardId, String topicId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + topicBoardId + TOPICS + topicId + COMMENTS)
        .then();
  }

  @Step("Получить комментарий к задаче по ID")
  public ValidatableResponse getTopicCommentById(String topicBoardId, String topicId, String topicCommentId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + topicBoardId + TOPICS + topicId + COMMENTS + topicCommentId)
        .then();
  }

  @Step("Обновить комментарий к задаче по ID")
  public ValidatableResponse updateTopicComment(String topicBoardId, String topicId, String topicCommentId, Comment updatedComment) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedComment)
        .when()
        .put(API_PROJECTS + topicBoardId + TOPICS + topicId + COMMENTS + topicCommentId)
        .then();
  }

  @Step("Удалить комментарий к задаче по ID")
  public ValidatableResponse deleteTopicComment(String topicBoardId, String topicId, String topicCommentId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + topicBoardId + TOPICS + topicId + COMMENTS + topicCommentId)
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
