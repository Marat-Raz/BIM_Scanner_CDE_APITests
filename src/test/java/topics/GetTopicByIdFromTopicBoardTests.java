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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicByIdFromTopicBoardTests extends TopicsBaseTests {

  private static ValidatableResponse addTopicsResponse;
  private static TopicsClient topicsClient = new TopicsClient();
  private static TopicsFactory topicsFactory = new TopicsFactory();
  private static Topics topic;
  private ValidatableResponse getTopicByIdResponse;
  private static String topicId;

  @BeforeAll
  public static void createTopic() {
    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicInTopicBoard(topicBoardId, topic);
    topicId = addTopicsResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить задачу по его id и id доски задач")
  public void getTopicByIdAndBoardIdTest() {
    getTopicByIdResponse = topicsClient.getTopicInTopicBoard(topicBoardId, topicId);
    statusCode = extractStatusCode(getTopicByIdResponse);
    ResponseTopics responseTopics = getTopicByIdResponse.extract().as(ResponseTopics.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(topic.getTitle(), responseTopics.getTitle()),
        () -> assertEquals(topicId, responseTopics.getId())
    );
  }

}
