package topicboards;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardByIdTests extends TopicBoardsBaseTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private ValidatableResponse getTopicBoardResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить доску задач по его id")
  public void getTopicBoardByIdTest() {
    getTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    statusCode = extractStatusCode(getTopicBoardResponse);
    responseTopicBoard = getTopicBoardResponse.extract()
        .as(ResponseTopicBoards.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(topicBoard.getName(), responseTopicBoard.name),
        () -> assertEquals(projectId, responseTopicBoard.projectId)
    );
  }

}
