package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import models.topiccomments.TopicComment;

public class TopicCommentsClient extends Client {

  private final String TOPIC_COMMENTS = "api/projects/";

  @Step("Создать комментарий к задаче")
  public ValidatableResponse createTopicComment(String topicBoardId, String topicId, TopicComment comment) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(comment)
        .when()
        .post(TOPIC_COMMENTS + topicBoardId + "/topics/" + topicId + "/comments")
        .then();
  }

  @Step("Получить список комментариев к задаче")
  public ValidatableResponse getTopicComments(String topicBoardId, String topicId,
      Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(TOPIC_COMMENTS + topicBoardId + "/topics/" + topicId + "/comments")
        .then();
  }

  @Step("Получить комментарий к задаче по ID")
  public ValidatableResponse getTopicCommentById(String topicBoardId, String topicId, String topicCommentId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPIC_COMMENTS + topicBoardId + "/topics/" + topicId + "/comments/" + topicCommentId)
        .then();
  }

  @Step("Обновить комментарий к задаче по ID")
  public ValidatableResponse updateTopicComment(String topicBoardId, String topicId, String topicCommentId, TopicComment updatedComment) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedComment)
        .when()
        .put(TOPIC_COMMENTS + topicBoardId + "/topics/" + topicId + "/comments/" + topicCommentId)
        .then();
  }

  @Step("Удалить комментарий к задаче по ID")
  public ValidatableResponse deleteTopicComment(String topicBoardId, String topicId, String topicCommentId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(TOPIC_COMMENTS + topicBoardId + "/topics/" + topicId + "/comments/" + topicCommentId)
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