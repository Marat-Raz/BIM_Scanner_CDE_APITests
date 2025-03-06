package topicboards;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteTopicBoardByIdTests extends TopicBoardsBaseTests {

  private static ValidatableResponse deleteTopicBoardResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить доску задач по его id")
  public void deleteTopicBoardByIdTest() {
    deleteTopicBoardResponse = topicBoardsClient.deleteTopicBoard(projectId, topicBoardId);
    statusCode = extractStatusCode(deleteTopicBoardResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }
}
