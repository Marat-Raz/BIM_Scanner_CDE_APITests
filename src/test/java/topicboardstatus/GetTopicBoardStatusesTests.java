package topicboardstatus;

import static models.statuses.StatusesType.DEFAULT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardsClient;
import client.TopicBoardStatusClient;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import models.statuses.Statuses;
import models.statuses.StatusesFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardStatusesTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse addStatusResponse;
  private static TopicBoardStatusClient topicBoardStatusClient = new TopicBoardStatusClient();
  private static final int statusesCount = 5;
  private ValidatableResponse getAllStatuses;
  private static List<Statuses> expectedStatuses = new ArrayList<>();

  @BeforeAll
  public static void createTopicBoardAndAddStatus() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();

    for (int i = 0; i < statusesCount; i++) {
      expectedStatuses.add(new StatusesFactory().createStatuses(DEFAULT));
    }
    for (Statuses status : expectedStatuses) {
      addStatusResponse = topicBoardStatusClient.addStatusToTopicBoard(topicBoardId, status);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все статусы доски задач")
  public void getTopicBoardStatusesTest() {
    getAllStatuses = topicBoardStatusClient.getTopicBoardStatuses(topicBoardId);
    List<Statuses> actualStatuses = Arrays.asList(getAllStatuses.extract().as(Statuses[].class));

    assertAll(
        () -> assertEquals(statusesCount, actualStatuses.size()),
        () -> assertEquals(new HashSet<>(expectedStatuses), new HashSet<>(actualStatuses))
    );
  }
}
