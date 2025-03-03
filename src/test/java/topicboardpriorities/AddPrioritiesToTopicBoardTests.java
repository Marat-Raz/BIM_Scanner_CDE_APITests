package topicboardpriorities;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static models.priorities.PrioritiesType.DEFAULT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardPrioritiesClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import models.priorities.ResponsePriorities;
import models.priorities.Priorities;
import models.priorities.PrioritiesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddPrioritiesToTopicBoardTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private ValidatableResponse addPrioritiesResponse;
  private TopicBoardPrioritiesClient topicBoardPrioritiesClient = new TopicBoardPrioritiesClient();
  private PrioritiesFactory prioritiesFactory = new PrioritiesFactory();
  private Priorities priority;

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
  public void addPrioritiesToTopicBoardTest() {
    priority = prioritiesFactory.createPriorities(DEFAULT);
    addPrioritiesResponse = topicBoardPrioritiesClient.addPrioritiesToTopicBoard(topicBoardId,
        priority);
    statusCode = extractStatusCode(addPrioritiesResponse);
    ResponsePriorities responsePriorities = addPrioritiesResponse.extract().as(ResponsePriorities.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(priority.getName(), responsePriorities.getName()),
        () -> assertEquals(priority.getColor(), responsePriorities.getColor())
    );
  }
}
