package topics;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.topics.ResponseTopics;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Topics(Задачи)")
@Story("Добавление задачи в доску задач")
public class AddTopicsToTopicBoardTests extends TopicsBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавить задачу в доску задач возвращает код 200")
  public void addTopicsToTopicBoardTest() {
    addTopicsResponse = topicsClient.createTopicOnTopicBoard(topicBoardId, topic);
    statusCode = extractStatusCode(addTopicsResponse);
    ResponseTopics responseTopics = addTopicsResponse.extract().as(ResponseTopics.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(topic.getTitle(), responseTopics.getTitle()),
        () -> assertEquals(topic.getDescription(), responseTopics.getDescription())
    );
  }
}
