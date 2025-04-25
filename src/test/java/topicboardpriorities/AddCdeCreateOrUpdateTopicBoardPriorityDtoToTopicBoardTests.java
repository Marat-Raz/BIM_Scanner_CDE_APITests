package topicboardpriorities;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeTopicBoardPriorityDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardPriority(«Приоритеты» в доске задач)")
@Story("Добавление «Приоритета» в доску задач")
public class AddCdeCreateOrUpdateTopicBoardPriorityDtoToTopicBoardTests extends
    TopicBoardCdeCreateOrUpdateTopicBoardPriorityDtoBaseTests {


  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать приоритет в доске задач")
  public void addPrioritiesToTopicBoardTest() {
    statusCode = extractStatusCode(addPrioritiesResponse);
    CdeTopicBoardPriorityDto cdeTopicBoardPriorityDto = addPrioritiesResponse.extract()
        .as(CdeTopicBoardPriorityDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(priority.getName(), cdeTopicBoardPriorityDto.getName()),
        () -> assertEquals(priority.getColor(), cdeTopicBoardPriorityDto.getColor())
    );
  }
}
