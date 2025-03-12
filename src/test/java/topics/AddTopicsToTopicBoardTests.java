package topics;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import models.topics.ResponseTopics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
        () -> assertEquals(topic.getDescription(), responseTopics.getDescription()),
        () -> assertEquals(topic.getDueDate(), responseTopics.getDueDate())
    );
  }
}
