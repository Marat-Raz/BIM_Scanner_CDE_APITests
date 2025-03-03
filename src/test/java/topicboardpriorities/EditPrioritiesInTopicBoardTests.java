package topicboardpriorities;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static models.priorities.PrioritiesType.DEFAULT;
import static org.apache.http.HttpStatus.SC_OK;
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

public class EditPrioritiesInTopicBoardTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse addPrioritiesResponse;
  private static ValidatableResponse editPrioritiesResponse;
  private static TopicBoardPrioritiesClient topicBoardPrioritiesClient = new TopicBoardPrioritiesClient();
  private static PrioritiesFactory prioritiesFactory = new PrioritiesFactory();
  private static Priorities priority;
  private static String priorityId;

  @BeforeAll
  public static void createTopicBoardAndAddPriority() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
    priority = prioritiesFactory.createPriorities(DEFAULT);
    addPrioritiesResponse = topicBoardPrioritiesClient.addPrioritiesToTopicBoard(topicBoardId, priority);
    priorityId = addPrioritiesResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Редактировать параметр «name» статуса в доске задач")
  public void editPrioritiesInTopicBoardTest() {
    Priorities editablePriority = priority;
    editablePriority.setName("newName");
    editPrioritiesResponse = topicBoardPrioritiesClient
        .editPrioritiesInTopicBoard(topicBoardId, priorityId, editablePriority);
    statusCode = extractStatusCode(editPrioritiesResponse);
    ResponsePriorities editedPriorityFromResponse =
        editPrioritiesResponse.extract().as(ResponsePriorities.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(editablePriority.getName(), editedPriorityFromResponse.getName());
  }

}
