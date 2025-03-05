package topicboards;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import models.topicboards.ResponseTopicBoards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateTopicBoardTests extends TopicBoardsBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать доску задач в корне проекта")
  public void createNewTopicBoardTest() {
    statusCode = extractStatusCode(createTopicBoardsResponse);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals("TopicBoard", responseTopicBoards.type),
        () -> assertEquals(topicBoard.getName(), responseTopicBoards.name),
        () -> assertEquals(projectId, responseTopicBoards.projectId)
    );
  }

  // todo реализовать тесты из TestIT
}
