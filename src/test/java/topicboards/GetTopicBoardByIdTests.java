package topicboards;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicBoardsClient;
import dto.generated.CdeTopicBoardDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoards(Доски задач)")
@Story("Получение доску задач по id")
public class GetTopicBoardByIdTests extends CreateTopicBoardBaseTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private ValidatableResponse getTopicBoardResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить доску задач по его id")
  public void getTopicBoardByIdTest() {
    getTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    statusCode = extractStatusCode(getTopicBoardResponse);
    responseTopicBoard = getTopicBoardResponse.extract()
        .as(CdeTopicBoardDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(topicBoard.getName(), responseTopicBoard.getName()),
        () -> assertEquals(projectId, responseTopicBoard.getProjectId())
    );
  }

}
