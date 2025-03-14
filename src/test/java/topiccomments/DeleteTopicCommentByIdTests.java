package topiccomments;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteTopicCommentByIdTests extends TopicsCommentsBaseTests {

  private ValidatableResponse deleteTopicCommentResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Обновить комментарий к задаче")
  public void deleteTopicCommentByIdTest() {
    deleteTopicCommentResponse = topicCommentsClient
        .deleteTopicComment(topicBoardId, defaultTopicId, topicCommentId);
    statusCode = extractStatusCode(deleteTopicCommentResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }
}
