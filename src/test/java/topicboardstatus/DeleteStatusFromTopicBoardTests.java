package topicboardstatus;

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
@Feature("Раздел TopicBoardStatus(«Статусы» в досках задач)")
@Story("Удаление «Статусов» с доски задач")
public class DeleteStatusFromTopicBoardTests extends TopicBoardStatusBaseTests {

  private static ValidatableResponse deleteStatusResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить статус из доски задач")
  public void deleteStatusInTopicBoardTest() {
    deleteStatusResponse = topicBoardStatusClient.deleteTopicBoardStatuses(topicBoardId, statusId);
    statusCode = extractStatusCode(deleteStatusResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
