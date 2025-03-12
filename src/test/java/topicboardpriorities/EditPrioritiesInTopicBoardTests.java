package topicboardpriorities;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.priorities.Priorities;
import models.priorities.ResponsePriorities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class EditPrioritiesInTopicBoardTests extends TopicBoardPrioritiesBaseTests {

  private static ValidatableResponse editPrioritiesResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Редактировать параметр «name» приоритета в доске задач")
  public void editPrioritiesInTopicBoardTest() {
    Priorities editablePriority = priority;
    editablePriority.setName("newName");
    editPrioritiesResponse = topicBoardPrioritiesClient
        .editTopicBoardPriorities(topicBoardId, priorityId, editablePriority);
    statusCode = extractStatusCode(editPrioritiesResponse);
    ResponsePriorities editedPriorityFromResponse =
        editPrioritiesResponse.extract().as(ResponsePriorities.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(editablePriority.getName(), editedPriorityFromResponse.getName());
  }

}
