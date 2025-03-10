package topicboardpriorities;

import static models.priorities.PrioritiesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import models.priorities.Priorities;
import models.priorities.PrioritiesFactory;
import org.junit.jupiter.api.*;

public class GetTopicBoardPrioritiesTests extends TopicBoardPrioritiesBaseTests {

  private static List<Priorities> expectedPriorities = new ArrayList<>();
  private ValidatableResponse getAllPriorities;

  @BeforeEach
  public void addPriorityToTopicBoard() {
    expectedPriorities.add(priority);
    for (int i = 0; i < 5; i++) {
      expectedPriorities.add(new PrioritiesFactory().createPriorities(DEFAULT));
    }
    for (Priorities priority : expectedPriorities) {
      addPrioritiesResponse = topicBoardPrioritiesClient.addPrioritiesToTopicBoard(topicBoardId,
          priority);
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
