package topicboardstatus;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeCreateOrUpdateTopicBoardStatusDto;
import dto.generated.CdeTopicBoardStatusDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardStatus(«Статусы» в досках задач)")
@Story("Редактирование «Статусов» в доске задач")
public class EditStatusOnTopicBoardTests extends TopicBoardStatusBaseTests {

  private static ValidatableResponse editStatusResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Редактировать параметр «name» статуса в доске задач")
  public void editStatusInTopicBoardTest() {
    CdeCreateOrUpdateTopicBoardStatusDto editableStatus = status;
    editableStatus.setName("newName");
    editStatusResponse = topicBoardStatusClient
        .editTopicBoardStatuses(topicBoardId, statusId, editableStatus);
    statusCode = extractStatusCode(editStatusResponse);
    CdeTopicBoardStatusDto editedStatusFromResponse =
        editStatusResponse.extract().as(CdeTopicBoardStatusDto.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(editableStatus.getName(), editedStatusFromResponse.getName());
  }

}
