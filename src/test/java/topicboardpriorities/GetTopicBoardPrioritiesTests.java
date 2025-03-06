package topicboardpriorities;

import static models.priorities.PrioritiesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicBoardPrioritiesClient;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import models.priorities.Priorities;
import models.priorities.PrioritiesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardPrioritiesTests extends TopicBoardPrioritiesBaseTests {

  private static TopicBoardPrioritiesClient topicBoardPrioritiesClient = new TopicBoardPrioritiesClient();
  private ValidatableResponse getAllPriorities;
  private static List<Priorities> expectedPriorities = new ArrayList<>();

  @BeforeAll
  public static void addPriorityToTopicBoard() {
    expectedPriorities.add(priority);
    for (int i = 0; i < 5; i++) {
      expectedPriorities.add(new PrioritiesFactory().createPriorities(DEFAULT));
    }
    for (Priorities status : expectedPriorities) {
      addPrioritiesResponse = topicBoardPrioritiesClient.addPrioritiesToTopicBoard(topicBoardId,
          status);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все приоритеты доски задач")
  public void getTopicBoardPrioritiesTest() {
    getAllPriorities = topicBoardPrioritiesClient.getTopicBoardPriorities(topicBoardId);
    List<Priorities> actualPriorities = Arrays.asList(
        getAllPriorities.extract().as(Priorities[].class));

    assertEquals(new HashSet<>(expectedPriorities), new HashSet<>(actualPriorities));
  }
}
