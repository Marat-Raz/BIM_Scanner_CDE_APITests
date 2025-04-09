package topicboardpriorities;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.priorities.ResponsePriorities;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardPriority(«Приоритеты» в доске задач)")
@Story("Добавление «Приоритета» в доску задач")
public class AddPrioritiesToTopicBoardTests extends TopicBoardPrioritiesBaseTests {


  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать приоритет в доске задач")
  public void addPrioritiesToTopicBoardTest() {
    statusCode = extractStatusCode(addPrioritiesResponse);
    ResponsePriorities responsePriorities = addPrioritiesResponse.extract()
        .as(ResponsePriorities.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(priority.getName(), responsePriorities.getName()),
        () -> assertEquals(priority.getColor(), responsePriorities.getColor())
    );
  }
}
