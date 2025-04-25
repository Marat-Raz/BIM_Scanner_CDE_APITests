package topicboardgroups;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardGroups(Группа досок задач)")
@Story("Создание группы досок задач")
public class CreateCdeCreateTopicBoardDtoGroupTests extends TopicBoardGroupBaseTests {
// todo   private ValidatableResponse createResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("При создании в проекте группы досок задач код ответа 200")
  public void createTopicBoardsGroupTest() {
    statusCode = extractStatusCode(createTopicBoardsGroupResponse);
    // fixme statusCode при появлении новых тестов будет неверным

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals("TopicBoardGroup", cdeTopicBoardGroupDto.type),
        () -> assertEquals(cdeCreateTopicBoardGroupDto.getName(), cdeTopicBoardGroupDto.name),
        () -> assertEquals(projectId, cdeTopicBoardGroupDto.projectId)
    );
  }

  // todo реализовать тесты из TestIT

}
