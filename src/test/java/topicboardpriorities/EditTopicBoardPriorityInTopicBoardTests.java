package topicboardpriorities;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeCreateOrUpdateTopicBoardPriorityDto;
import dto.generated.CdeTopicBoardPriorityDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardPriority(«Приоритеты» в доске задач)")
@Story("Редактирование «Приоритетов» в доске задач")
public class EditTopicBoardPriorityInTopicBoardTests extends TopicBoardPriorityBaseTests {

  private static ValidatableResponse editPrioritiesResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Редактировать параметр «name» приоритета в доске задач")
  public void editPrioritiesInTopicBoardTest() {
    CdeCreateOrUpdateTopicBoardPriorityDto editablePriority = priority;
    editablePriority.setName("newName");
    editPrioritiesResponse = topicBoardPrioritiesClient
        .editTopicBoardPriorities(topicBoardId, priorityId, editablePriority);
    statusCode = extractStatusCode(editPrioritiesResponse);
    CdeTopicBoardPriorityDto editedPriorityFromResponse =
        editPrioritiesResponse.extract().as(CdeTopicBoardPriorityDto.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(editablePriority.getName(), editedPriorityFromResponse.getName());
  }

}
