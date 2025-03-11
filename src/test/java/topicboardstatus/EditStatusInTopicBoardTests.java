package topicboardstatus;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.statuses.ResponseStatuses;
import models.statuses.Statuses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class EditStatusInTopicBoardTests extends TopicBoardStatusBaseTests {

  private static ValidatableResponse editStatusResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Редактировать параметр «name» статуса в доске задач")
  public void editStatusInTopicBoardTest() {
    Statuses editableStatus = status;
    editableStatus.setName("newName");
    editStatusResponse = topicBoardStatusClient
        .editTopicBoardStatuses(topicBoardId, statusId, editableStatus);
    statusCode = extractStatusCode(editStatusResponse);
    ResponseStatuses editedStatusFromResponse =
        editStatusResponse.extract().as(ResponseStatuses.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(editableStatus.getName(), editedStatusFromResponse.getName());
  }

}
