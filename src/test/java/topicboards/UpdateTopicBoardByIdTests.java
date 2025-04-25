package topicboards;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeCreateTopicBoardDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoards(Доски задач)")
@Story("Редактирование доски задач")
public class UpdateTopicBoardByIdTests extends CdeCreateTopicBoardDtoBaseTests {

  private ValidatableResponse updateTopicBoardResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить группу досок задач по его id")
  public void updateTopicBoardByIdTest() {
    CdeCreateTopicBoardDto newTopicBoard = topicBoard;
    newTopicBoard.setName("newName");
    updateTopicBoardResponse = topicBoardsClient
        .updateTopicBoard(projectId, topicBoardId, newTopicBoard);
    statusCode = extractStatusCode(updateTopicBoardResponse);
    CdeTopicBoardDto cdeTopicBoardDto = updateTopicBoardResponse.extract()
        .as(CdeTopicBoardDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(newTopicBoard.getName(), cdeTopicBoardDto.getName()),
        () -> assertEquals(topicBoardId, cdeTopicBoardDto.getId()),
        () -> assertEquals(projectId, cdeTopicBoardDto.projectId)
    );
  }

}
