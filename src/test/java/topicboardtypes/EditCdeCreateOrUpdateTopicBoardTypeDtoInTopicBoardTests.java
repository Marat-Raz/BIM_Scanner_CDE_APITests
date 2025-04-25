package topicboardtypes;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeTopicBoardTypeDto;
import dto.generated.CdeCreateOrUpdateTopicBoardTypeDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardType(«Типы» в досках задач)")
@Story("Редактирование «Типов» в доске задач")
public class EditCdeCreateOrUpdateTopicBoardTypeDtoInTopicBoardTests extends TopicBoardTypeBaseTests {

  private static ValidatableResponse editTypeResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Редактировать параметр «name» типа задач в доске задач")
  public void editTypesInTopicBoardTest() {
    CdeCreateOrUpdateTopicBoardTypeDto editableType = type;
    editableType.setName("newName");
    editTypeResponse = topicBoardTypesClient
        .editTopicBoardTypes(topicBoardId, typeId, editableType);
    statusCode = extractStatusCode(editTypeResponse);
    CdeTopicBoardTypeDto editedTypeFromResponse =
        editTypeResponse.extract().as(CdeTopicBoardTypeDto.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(editableType.getName(), editedTypeFromResponse.getName());
  }

}
