package topics;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicsClient;
import io.restassured.response.ValidatableResponse;
import dtomodels.topics.ResponseTopics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdatesExistingTopicTests extends TopicsBaseTests {

  private static TopicsClient topicsClient = new TopicsClient();
  private ValidatableResponse updateTopicByIdResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Обновить задачу по его id и по id доски задач")
  public void updateTopicInBoardTest() {
    responseTopic.setTitle("new Title");
    updateTopicByIdResponse = topicsClient
        .updateTopicOnTopicBoard(topicBoardId, responseTopic.getId(), responseTopic);
    ResponseTopics updatedTopics = updateTopicByIdResponse.extract().as(ResponseTopics.class);
    statusCode = extractStatusCode(updateTopicByIdResponse);

    assertEquals(SC_OK, statusCode);
    assertEquals(responseTopic.getTitle(), updatedTopics.getTitle());
  }

}
