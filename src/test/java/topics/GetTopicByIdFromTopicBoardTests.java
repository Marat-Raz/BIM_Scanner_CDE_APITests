package topics;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.topics.ResponseTopics;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Topics(Задачи)")
@Story("Получение задач по id из доски задач")
public class GetTopicByIdFromTopicBoardTests extends TopicsBaseTests {

  private ValidatableResponse getTopicByIdResponse;


  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить задачу по его id и id доски задач")
  public void getTopicByIdAndBoardIdTest() {
    getTopicByIdResponse = topicsClient.getTopicOnTopicBoard(topicBoardId, defaultTopicId);
    statusCode = extractStatusCode(getTopicByIdResponse);
    ResponseTopics responseTopics = getTopicByIdResponse.extract().as(ResponseTopics.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(topic.getTitle(), responseTopics.getTitle()),
        () -> assertEquals(defaultTopicId, responseTopics.getId())
    );
  }

}
