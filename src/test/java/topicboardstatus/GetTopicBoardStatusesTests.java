package topicboardstatus;

import static dtomodels.statuses.StatusesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.statuses.Statuses;
import dtomodels.statuses.StatusesFactory;
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
@Feature("Раздел TopicBoardStatus(«Статусы» в досках задач)")
@Story("Получение «Статусов» с доски задач")
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
      addStatusResponse = topicBoardStatusClient.addTopicBoardStatuses(topicBoardId, status);
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
