package topicboards;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdateTopicBoardByIdTests extends StartTests {

  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static ValidatableResponse createTopicBoardsResponse;
  private ValidatableResponse updateTopicBoardResponse;
  static String topicBoardId;

  @BeforeAll
  @Step("Создать в проекте группу досок задач")
  public static void createTopicBoardOnProject() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId,
        topicBoard);
    topicBoardId = createTopicBoardsResponse.extract().path("id");
  }

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
