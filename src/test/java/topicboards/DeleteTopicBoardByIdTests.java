package topicboards;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteTopicBoardByIdTests extends StartTests {

  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static ValidatableResponse createTopicBoardsResponse;
  private static ValidatableResponse deleteTopicBoardResponse;
  private static String topicBoardId;

  @BeforeAll
  @Step("Создать проект, в ней создать доску задач")
  public static void createProject() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    topicBoardId = createTopicBoardsResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить доску задач по его id")
  public void deleteTopicBoardByIdTest() {
    deleteTopicBoardResponse = topicBoardsClient.deleteTopicBoard(projectId, topicBoardId);
    statusCode = extractStatusCode(deleteTopicBoardResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }
}
