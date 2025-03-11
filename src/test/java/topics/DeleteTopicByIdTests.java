package topics;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteTopicByIdTests extends TopicsBaseTests {

  private ValidatableResponse deleteTopicByIdResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить задачу по его id")
  public void deleteTopicByIdTest() {
    deleteTopicByIdResponse = topicsClient.deleteTopicOnTopicBoard(topicBoardId, defaultTopicId);
    statusCode = extractStatusCode(deleteTopicByIdResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }


}
