package topiccomments;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import dtomodels.comment.ResponseTopicComment;
import dtomodels.comment.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdateTopicCommentTests extends TopicsCommentsBaseTests {

  private ValidatableResponse updateTopicCommentResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Обновить комментарий к задаче")
  public void updateTopicCommentTest() {
    Comment newComment = comment;
    comment.setComment("New comment");
    updateTopicCommentResponse = topicCommentsClient
        .updateTopicComment(topicBoardId, defaultTopicId, topicCommentId, newComment);
    statusCode = extractStatusCode(updateTopicCommentResponse);
    responseTopicComment = updateTopicCommentResponse.extract().as(ResponseTopicComment.class);
    String expectedTopicCommentId = responseTopicComment.getId();

    assertEquals(SC_OK, statusCode);
    assertEquals(topicCommentId, expectedTopicCommentId);
    assertEquals(comment.getComment(), responseTopicComment.getComment());
  }
}
