package topicboardpriorities;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static models.priorities.PrioritiesType.DEFAULT;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.TopicBoardPrioritiesClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import models.priorities.Priorities;
import models.priorities.PrioritiesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeletePrioritiesFromTopicBoardTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse addPrioritiesResponse;
  private static ValidatableResponse deletePrioritiesResponse;
  private static TopicBoardPrioritiesClient topicBoardPrioritiesClient = new TopicBoardPrioritiesClient();
  private static PrioritiesFactory prioritiesFactory = new PrioritiesFactory();
  private static Priorities priority;
  private static String priorityId;

  @BeforeAll
  public static void createTopicBoardAndAddType() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
    priority = prioritiesFactory.createPriorities(DEFAULT);
    addPrioritiesResponse = topicBoardPrioritiesClient.addPrioritiesToTopicBoard(topicBoardId,
        priority);
    priorityId = addPrioritiesResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить приоритет задачи из доски задач")
  public void deletePrioritiesInTopicBoardTest() {
    deletePrioritiesResponse = topicBoardPrioritiesClient.deletePrioritiesInTopicBoard(topicBoardId, priorityId);
    statusCode = extractStatusCode(deletePrioritiesResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
