package topics;

import static models.topics.TopicType.DEFAULT_TOPIC;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.topics.ResponseTopics;
import models.topics.Topics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddTopicsToTopicBoardTests extends TopicsBaseTests {

  @BeforeEach
  public void addTopic() {
    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicInTopicBoard(topicBoardId, topic);
    responseTopic = addTopicsResponse.extract().as(ResponseTopics.class);
    defaultTopicId = responseTopic.getId();
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавить задачу в доску задач возвращает код 200")
  public void addTopicsToTopicBoardTest() {
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
