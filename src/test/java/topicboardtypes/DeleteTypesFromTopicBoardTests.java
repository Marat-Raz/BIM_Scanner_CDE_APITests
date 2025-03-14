package topicboardtypes;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteTypesFromTopicBoardTests extends TopicBoardTypeBaseTests {

  private static ValidatableResponse deleteTypesResponse;


  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить тип задачи из доски задач")
  public void deleteTypesInTopicBoardTest() {
    deleteTypesResponse = topicBoardTypesClient.deleteTopicBoardTypes(topicBoardId, typeId);
    statusCode = extractStatusCode(deleteTypesResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
