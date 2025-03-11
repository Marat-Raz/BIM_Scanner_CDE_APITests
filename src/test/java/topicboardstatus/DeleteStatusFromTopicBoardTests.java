package topicboardstatus;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteStatusFromTopicBoardTests extends TopicBoardStatusBaseTests {

  private static ValidatableResponse deleteStatusResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить статус из доски задач")
  public void deleteStatusInTopicBoardTest() {
    deleteStatusResponse = topicBoardStatusClient.deleteTopicBoardStatuses(topicBoardId, statusId);
    statusCode = extractStatusCode(deleteStatusResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
