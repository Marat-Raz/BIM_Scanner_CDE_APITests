package topicboardpriorities;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeletePrioritiesFromTopicBoardTests extends TopicBoardPrioritiesBaseTests {

  private static ValidatableResponse deletePrioritiesResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить приоритет задачи из доски задач")
  public void deletePrioritiesInTopicBoardTest() {
    deletePrioritiesResponse = topicBoardPrioritiesClient.deletePrioritiesInTopicBoard(topicBoardId,
        priorityId);
    statusCode = extractStatusCode(deletePrioritiesResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
