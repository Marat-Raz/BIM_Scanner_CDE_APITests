package topicboardstatus;

import static models.statuses.StatusesType.DEFAULT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardsClient;
import client.base.TopicBoardStatusClient;
import io.restassured.response.ValidatableResponse;
import models.statuses.Statuses;
import models.statuses.StatusesFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteStatusFromTopicBoardTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse addStatusResponse;
  private static ValidatableResponse deleteStatusResponse;
  private static TopicBoardStatusClient topicBoardStatusClient = new TopicBoardStatusClient();
  private static StatusesFactory statusesFactory = new StatusesFactory();
  private static Statuses status;
  private static String statusId;

  @BeforeAll
  public static void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
    status = statusesFactory.createStatuses(DEFAULT);
    addStatusResponse = topicBoardStatusClient.addStatusToTopicBoard(topicBoardId, status);
    statusId = addStatusResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить статус из доски задач")
  public void createTopicBoardsGroupTest() {
    deleteStatusResponse = topicBoardStatusClient.deleteStatusInTopicBoard(topicBoardId, statusId);
    statusCode = extractStatusCode(deleteStatusResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
