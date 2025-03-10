package topicevents;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.response.ValidatableResponse;
import models.topicevents.ResponseTopicEventList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetAllTopicEventsInTopicBoardTests extends TopicEventsBaseTests {

  private ValidatableResponse getAllTopicEventsResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить уведомления об изменениях со всех задач")
  public void getAllTopicEventsInTopicBoardTest() {
    getAllTopicEventsResponse = topicEventsClient.getTopicEventsWithoutQueryParams(topicBoardId);
    ResponseTopicEventList responseTopicEventList = getAllTopicEventsResponse.extract()
        .as(ResponseTopicEventList.class);
    statusCode = extractStatusCode(getAllTopicEventsResponse);

    assertEquals(SC_OK, statusCode);
    assertNotNull(responseTopicEventList.getItems());
  }
}


