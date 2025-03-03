package topicboardpriorities;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static models.priorities.PrioritiesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardPrioritiesClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import models.priorities.Priorities;
import models.priorities.PrioritiesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardPrioritiesTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse addPrioritiesResponse;
  private static TopicBoardPrioritiesClient topicBoardPrioritiesClient = new TopicBoardPrioritiesClient();
  private static final int prioritiesCount = 5;
  private ValidatableResponse getAllPriorities;
  private static List<Priorities> expectedPriorities = new ArrayList<>();

  @BeforeAll
  public static void createTopicBoardAndAddPriority() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();

    for (int i = 0; i < prioritiesCount; i++) {
      expectedPriorities.add(new PrioritiesFactory().createPriorities(DEFAULT));
    }
    for (Priorities status : expectedPriorities) {
      addPrioritiesResponse = topicBoardPrioritiesClient.addPrioritiesToTopicBoard(topicBoardId, status);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все приоритеты доски задач")
  public void getTopicBoardPrioritiesTest() {
    getAllPriorities = topicBoardPrioritiesClient.getTopicBoardPriorities(topicBoardId);
    List<Priorities> actualPriorities = Arrays.asList(getAllPriorities.extract().as(Priorities[].class));

    assertAll(
        () -> assertEquals(prioritiesCount, actualPriorities.size()),
        () -> assertEquals(new HashSet<>(expectedPriorities), new HashSet<>(actualPriorities))
    );
  }
}
