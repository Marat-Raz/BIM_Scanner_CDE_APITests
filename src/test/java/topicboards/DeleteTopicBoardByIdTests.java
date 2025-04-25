package topicboards;

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
@Feature("Раздел TopicBoards(Доски задач)")
@Story("Удаление доски задач")
public class DeleteTopicBoardByIdTests extends CdeCreateTopicBoardDtoBaseTests {

  private static ValidatableResponse deleteTopicBoardResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить доску задач по его id")
  public void deleteTopicBoardByIdTest() {
    deleteTopicBoardResponse = topicBoardsClient.deleteTopicBoard(projectId, topicBoardId);
    statusCode = extractStatusCode(deleteTopicBoardResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }
}
