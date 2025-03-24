package topicboardtypes;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import dtomodels.types.ResponseTypes;
import dtomodels.types.Types;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class EditTypesInTopicBoardTests extends TopicBoardTypeBaseTests {

  private static ValidatableResponse editTypeResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Редактировать параметр «name» типа задач в доске задач")
  public void editTypesInTopicBoardTest() {
    Types editableType = type;
    editableType.setName("newName");
    editTypeResponse = topicBoardTypesClient
        .editTopicBoardTypes(topicBoardId, typeId, editableType);
    statusCode = extractStatusCode(editTypeResponse);
    ResponseTypes editedTypeFromResponse =
        editTypeResponse.extract().as(ResponseTypes.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(editableType.getName(), editedTypeFromResponse.getName());
  }

}
