package topics;

import static models.topics.TopicType.DEFAULT_TOPIC;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicsClient;
import io.restassured.response.ValidatableResponse;
import models.topics.ResponseTopics;
import models.topics.Topics;
import models.topics.TopicsFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddTopicsToTopicBoardTests extends TopicsBaseTests {

  private ValidatableResponse addTopicsResponse;
  private TopicsClient topicsClient = new TopicsClient();
  private TopicsFactory topicsFactory = new TopicsFactory();
  private Topics topic;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавить задачи в доску задач")
  public void addTopicsToTopicBoardTest() {
    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicInTopicBoard(topicBoardId, topic);
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
