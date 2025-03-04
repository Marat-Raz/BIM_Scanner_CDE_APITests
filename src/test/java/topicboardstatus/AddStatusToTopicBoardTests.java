package topicboardstatus;

import static models.statuses.StatusesType.DEFAULT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.TopicBoardsClient;
import client.TopicBoardStatusClient;
import io.restassured.response.ValidatableResponse;
import models.statuses.ResponseStatuses;
import models.statuses.Statuses;
import models.statuses.StatusesFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddStatusToTopicBoardTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private ValidatableResponse addStatusResponse;
  private TopicBoardStatusClient topicBoardStatusClient = new TopicBoardStatusClient();
  private StatusesFactory statusesFactory = new StatusesFactory();
  private Statuses status;

  @BeforeAll
  public static void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать статус в доске задач")
  public void addStatusToTopicBoardTest() {
    status = statusesFactory.createStatuses(DEFAULT);
    addStatusResponse = topicBoardStatusClient.addStatusToTopicBoard(topicBoardId, status);
    statusCode = extractStatusCode(addStatusResponse);
    ResponseStatuses responseStatuses = addStatusResponse.extract().as(ResponseStatuses.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(status.getName(), responseStatuses.getName()),
        () -> assertEquals(status.getColor(), responseStatuses.getColor())
    );
  }
}
