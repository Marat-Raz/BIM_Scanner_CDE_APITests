package topicboards;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdateTopicBoardByIdTests extends TopicBoardsBaseTests {

  private ValidatableResponse updateTopicBoardResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить группу досок задач по его id")
  public void updateTopicBoardByIdTest() {
    TopicBoards newTopicBoard = topicBoard;
    newTopicBoard.setName("newName");
    updateTopicBoardResponse = topicBoardsClient
        .updateTopicBoard(projectId, topicBoardId, newTopicBoard);
    statusCode = extractStatusCode(updateTopicBoardResponse);
    ResponseTopicBoards responseTopicBoards = updateTopicBoardResponse.extract()
        .as(ResponseTopicBoards.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(newTopicBoard.getName(), responseTopicBoards.getName()),
        () -> assertEquals(topicBoardId, responseTopicBoards.getId()),
        () -> assertEquals(projectId, responseTopicBoards.projectId)
    );
  }

}
