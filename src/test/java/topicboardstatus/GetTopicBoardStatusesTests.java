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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardStatusesTests extends TopicBoardStatusBaseTests {

  private ValidatableResponse getAllStatuses;
  private static List<Statuses> expectedStatuses = new ArrayList<>();

  @BeforeAll
  public static void addStatusesToTopicBoard() {
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
