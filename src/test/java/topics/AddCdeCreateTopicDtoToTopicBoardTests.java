package topics;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeTopicDetailsDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Topics(Задачи)")
@Story("Добавление задачи в доску задач")
public class AddCdeCreateTopicDtoToTopicBoardTests extends CdeCreateTopicDtoBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавить задачу в доску задач возвращает код 200")
  public void addTopicsToTopicBoardTest() {
    addTopicsResponse = topicsClient.createTopicOnTopicBoard(topicBoardId, topic);
    statusCode = extractStatusCode(addTopicsResponse);
    CdeTopicDetailsDto cdeTopicDetailsDto = addTopicsResponse.extract().as(CdeTopicDetailsDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(topic.getTitle(), cdeTopicDetailsDto.getTitle()),
        () -> assertEquals(topic.getDescription(), cdeTopicDetailsDto.getDescription())
    );
  }
}
