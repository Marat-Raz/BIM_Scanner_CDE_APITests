package topics;

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
@Feature("Раздел Topics(Задачи)")
@Story("Удаление задачи из доски задач")
public class DeleteTopicByIdTests extends TopicBaseTests {

  private ValidatableResponse deleteTopicByIdResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить задачу по его id")
  public void deleteTopicByIdTest() {
    deleteTopicByIdResponse = topicsClient.deleteTopicOnTopicBoard(topicBoardId, defaultTopicId);
    statusCode = extractStatusCode(deleteTopicByIdResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }


}
