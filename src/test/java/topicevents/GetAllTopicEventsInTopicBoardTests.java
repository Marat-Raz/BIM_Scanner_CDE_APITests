package topicevents;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dtomodels.PaginatedResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicEvents(Уведомления в задачах)")
@Story("Получение всех уведомлений задач в определенной доске задач")
public class GetAllTopicEventsInTopicBoardTests extends TopicEventsBaseTests {

  private ValidatableResponse getAllTopicEventsResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить уведомления об изменениях со всех задач")
  public void getAllTopicEventsInTopicBoardTest() {
    getAllTopicEventsResponse = topicEventsClient.getTopicEventsWithoutQueryParams(topicBoardId);
    PaginatedResponse topicEventList = getAllTopicEventsResponse.extract()
        .as(PaginatedResponse.class);
    statusCode = extractStatusCode(getAllTopicEventsResponse);

    assertEquals(SC_OK, statusCode);
    assertNotNull(topicEventList.getItems());
  }
}


