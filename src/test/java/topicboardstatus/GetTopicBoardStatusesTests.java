package topicboardstatus;

import static models.statuses.StatusesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import models.statuses.Statuses;
import models.statuses.StatusesFactory;
import org.junit.jupiter.api.*;

public class GetTopicBoardStatusesTests extends TopicBoardStatusBaseTests {

  private ValidatableResponse getAllStatuses;
  private static List<Statuses> expectedStatuses = new ArrayList<>();

  @BeforeEach
  public void addStatusesToTopicBoard() {
    expectedStatuses.add(status);
    for (int i = 0; i < 5; i++) {
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

    assertEquals(new HashSet<>(expectedStatuses), new HashSet<>(actualStatuses));
  }
}
