package topicboardpriorities;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardPriority(«Приоритеты» в доске задач)")
@Story("удаление «Приоритета» с доски задач")
public class DeletePrioritiesFromTopicBoardTests extends TopicBoardPrioritiesBaseTests {

  private static ValidatableResponse deletePrioritiesResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить приоритет задачи из доски задач")
  public void deletePrioritiesInTopicBoardTest() {
    deletePrioritiesResponse = topicBoardPrioritiesClient.deleteTopicBoardPriorities(topicBoardId,
        priorityId);
    statusCode = extractStatusCode(deletePrioritiesResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
