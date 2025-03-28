package topicboardpriorities;

import static dtomodels.priorities.PrioritiesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.priorities.Priorities;
import dtomodels.priorities.PrioritiesFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardPriority(«Приоритеты» в доске задач)")
@Story("Получение «Приоритетов» с доски задач")
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
      addPrioritiesResponse = topicBoardPrioritiesClient.addTopicBoardPriorities(topicBoardId,
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
