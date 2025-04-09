package topiccomments;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import dtomodels.comment.ResponseTopicComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicCommentByIdTests extends TopicsCommentsBaseTests {

  private ValidatableResponse getsTopicCommentsById;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить комментарий задачи по его id")
  public void getTopicCommentByIdTest() {
    getsTopicCommentsById = topicCommentsClient
        .getTopicCommentById(topicBoardId, defaultTopicId, topicCommentId);
    statusCode = extractStatusCode(getsTopicCommentsById);
    responseTopicComment = getsTopicCommentsById.extract()
        .as(ResponseTopicComment.class);
    String newTopicCommentId = responseTopicComment.getId();

    assertEquals(SC_OK, statusCode);
    assertEquals(topicCommentId, newTopicCommentId);
  }

}
