package topicboardstatus;

import static dtomodels.statuses.StatusesType.DEFAULT;
import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;

import basetests.StartTests;
import client.TopicBoardStatusClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import dtomodels.statuses.ResponseStatuses;
import dtomodels.statuses.Statuses;
import dtomodels.statuses.StatusesFactory;
import dtomodels.topicboards.ResponseTopicBoards;
import dtomodels.topicboards.TopicBoards;
import dtomodels.topicboards.TopicBoardsFactory;
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
  protected ValidatableResponse addStatusResponse;
  protected Statuses status;
  protected String statusId;
  protected ResponseStatuses responseStatuses;

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
    addStatusResponse = topicBoardStatusClient.addTopicBoardStatuses(topicBoardId, status);
    responseStatuses = addStatusResponse.extract().as(ResponseStatuses.class);
    statusId = responseStatuses.getId();
  }

}
