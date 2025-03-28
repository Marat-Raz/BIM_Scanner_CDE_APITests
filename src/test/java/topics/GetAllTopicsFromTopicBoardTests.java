package topics;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.topics.ResponseFromGetAllTopics;
import dtomodels.topics.ResponseTopics;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Topics(Задачи)")
@Story("Получение всех задач с доски задач")
public class GetAllTopicsFromTopicBoardTests extends TopicsBaseTests {

  private ValidatableResponse getListOfTopicsResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все задачи из доски задач")
  public void addTopicsToTopicBoardTest() {
    getListOfTopicsResponse = topicsClient.getListOfTopicsFromTopicBoardWithoutQueryOptions(
        topicBoardId);
    statusCode = extractStatusCode(getListOfTopicsResponse);
    ResponseFromGetAllTopics responseFromGetAllTopics = getListOfTopicsResponse
        .extract().as(ResponseFromGetAllTopics.class);
    ArrayList<ResponseTopics> arrayOfTopics = responseFromGetAllTopics.getItems();

    assertEquals(SC_OK, statusCode);
    assertEquals(responseFromGetAllTopics.getTotalCount(),
        arrayOfTopics.size()); // fixme нужно другим образом сравнить
  }

}
