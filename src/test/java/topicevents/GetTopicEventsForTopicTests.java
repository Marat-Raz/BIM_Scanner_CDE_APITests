package topicevents;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.response.ValidatableResponse;
import models.topicevents.ResponseTopicEventList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicEventsForTopicTests extends TopicEventsBaseTests {

  private ValidatableResponse getTopicEventsResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить уведомления об изменениях задачи")
  public void getTopicEventsInTopicBoardTest() {
    getTopicEventsResponse = topicEventsClient
        .getTopicEventsForSpecificTopicWithoutQueryParams(topicBoardId, defaultTopicId);
    ResponseTopicEventList responseTopicEventList = getTopicEventsResponse.extract()
        .as(ResponseTopicEventList.class);
    statusCode = extractStatusCode(getTopicEventsResponse);

    assertEquals(SC_OK, statusCode);
    assertNotNull(responseTopicEventList.getItems());
  }
}
