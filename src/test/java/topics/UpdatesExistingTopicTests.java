package topics;

import static models.topics.TopicType.DEFAULT_TOPIC;
import static org.apache.http.HttpStatus.SC_OK;
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

public class UpdatesExistingTopicTests extends TopicsBaseTests {

  private static ValidatableResponse addTopicsResponse;
  private static TopicsClient topicsClient = new TopicsClient();
  private static TopicsFactory topicsFactory = new TopicsFactory();
  private static Topics topic;
  private static String topicId;
  private static ResponseTopics responseTopics;
  private ValidatableResponse updateTopicByIdResponse;

  @BeforeAll
  public static void createTopic() {
    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicInTopicBoard(topicBoardId, topic);
    //topicId = addTopicsResponse.extract().path("id");
    responseTopics = addTopicsResponse.extract().as(ResponseTopics.class);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Обновить задачу по его id и по id доски задач")
  public void updateTopicInBoardTest() {
    responseTopics.setTitle("new Title");
    updateTopicByIdResponse = topicsClient
        .updateTopicInTopicBoard(topicBoardId, responseTopics.getId(), responseTopics);
    ResponseTopics updatedTopics = updateTopicByIdResponse.extract().as(ResponseTopics.class);
    statusCode = extractStatusCode(updateTopicByIdResponse);

    assertEquals(SC_OK, statusCode);
    assertEquals(responseTopics.getTitle(), updatedTopics.getTitle());
  }

}
