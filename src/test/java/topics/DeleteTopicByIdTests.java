package topics;

import static models.topics.TopicType.DEFAULT_TOPIC;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicsClient;
import io.restassured.response.ValidatableResponse;
import models.topics.Topics;
import models.topics.TopicsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteTopicByIdTests extends TopicsBaseTests {

  private static ValidatableResponse addTopicsResponse;
  private static TopicsClient topicsClient = new TopicsClient();
  private static TopicsFactory topicsFactory = new TopicsFactory();
  private static Topics topic;
  private ValidatableResponse deleteTopicByIdResponse;
  private static String topicId;

  @BeforeAll
  public static void createTopic() {
    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicInTopicBoard(topicBoardId, topic);
    topicId = addTopicsResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить задачу по его id")
  public void deleteTopicByIdTest() {
    deleteTopicByIdResponse = topicsClient.deleteTopicInTopicBoard(topicBoardId, topicId);
    statusCode = extractStatusCode(deleteTopicByIdResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }


}
