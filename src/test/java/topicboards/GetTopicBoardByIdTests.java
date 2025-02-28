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

public class GetTopicBoardByIdTests extends StartTests {

  private static ResponseTopicBoards responseTopicBoard;
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static ValidatableResponse createTopicBoardsResponse;
  private ValidatableResponse getTopicBoardResponse;
  private static String topicBoardId;

  @BeforeAll
  @Step("Создать проект, в ней создать доску задач")
  public static void createTopicBoardOnProject() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    topicBoardId = createTopicBoardsResponse.extract().path("id");
  }

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
        () -> assertEquals("TopicBoard", responseTopicBoard.type),
        () -> assertEquals(topicBoard.getName(), responseTopicBoard.name),
        () -> assertEquals(projectId, responseTopicBoard.projectId)
    );
  }

}
