package topiccomments;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.topiccomments.ResponseTopicComment;
import dtomodels.topiccomments.TopicComment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicComments(Комментарии к задачам)")
@Story("Обновление(изменение) комментария к задаче")
public class UpdateTopicCommentTests extends TopicsCommentsBaseTests {

  private ValidatableResponse updateTopicCommentResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Обновить комментарий к задаче")
  public void updateTopicCommentTest() {
    TopicComment newTopicComment = topicComment;
    topicComment.setComment("New comment");
    updateTopicCommentResponse = topicCommentsClient
        .updateTopicComment(topicBoardId, defaultTopicId, topicCommentId, newTopicComment);
    statusCode = extractStatusCode(updateTopicCommentResponse);
    responseTopicComment = updateTopicCommentResponse.extract().as(ResponseTopicComment.class);
    String expectedTopicCommentId = responseTopicComment.getId();

    assertEquals(SC_OK, statusCode);
    assertEquals(topicCommentId, expectedTopicCommentId);
    assertEquals(topicComment.getComment(), responseTopicComment.getComment());
  }
}
