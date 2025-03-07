package topicboardstatus;

import static models.statuses.StatusesType.DEFAULT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;

import basetests.StartTests;
import client.TopicBoardStatusClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import models.statuses.ResponseStatuses;
import models.statuses.Statuses;
import models.statuses.StatusesFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicBoardStatusBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static TopicBoardStatusClient topicBoardStatusClient = new TopicBoardStatusClient();
  protected static StatusesFactory statusesFactory = new StatusesFactory();
  protected static TopicBoards topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static String topicBoardId;
  protected static ValidatableResponse addStatusResponse;
  protected static Statuses status;
  protected static String statusId;
  protected static ResponseStatuses responseStatuses;

  @BeforeAll
  public static void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
  }

  @BeforeEach
  public void addStatus() {
    status = statusesFactory.createStatuses(DEFAULT);
    addStatusResponse = topicBoardStatusClient.addStatusToTopicBoard(topicBoardId, status);
    responseStatuses = addStatusResponse.extract().as(ResponseStatuses.class);
    statusId = responseStatuses.getId();
  }

}
