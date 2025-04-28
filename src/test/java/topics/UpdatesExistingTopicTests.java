package topics;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicsClient;
import dto.generated.CdeTopicDetailsDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Topics(Задачи)")
@Story("Обновление задачи в доске задач")
public class UpdatesExistingTopicTests extends TopicBaseTests {

  private static TopicsClient topicsClient = new TopicsClient();
  private ValidatableResponse updateTopicByIdResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Обновить задачу по его id и по id доски задач")
  public void updateTopicInBoardTest() {
    responseTopic.setTitle("new Title");
    updateTopicByIdResponse = topicsClient
        .updateTopicOnTopicBoard(topicBoardId, responseTopic.getId(), responseTopic);
    CdeTopicDetailsDto updatedTopics = updateTopicByIdResponse.extract().as(CdeTopicDetailsDto.class);
    statusCode = extractStatusCode(updateTopicByIdResponse);

    assertEquals(SC_OK, statusCode);
    assertEquals(responseTopic.getTitle(), updatedTopics.getTitle());
  }

}
