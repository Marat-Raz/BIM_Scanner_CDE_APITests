package topicboards;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateTopicBoardTests extends StartTests {

  private TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private TopicBoards topicBoard;
  private ValidatableResponse createTopicBoardsResponse;

  @BeforeEach
  public void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать доску задач в корне проекта")
  public void createTopicBoardsGroupTest() {
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
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
