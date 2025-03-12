package topics;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.topics.ResponseTopics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
