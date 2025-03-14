package topicboards;

import static models.customfields.customfieldstoedit.CustomFieldToEditType.IS_ENABLED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import models.customfields.customfieldstoedit.CustomFieldToEdit;
import models.customfields.customfieldstoedit.CustomFieldToEditFactory;
import models.customfields.customfieldstoedit.CustomFieldsToEdit;
import models.topicboards.ResponseTopicBoards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddCustomFieldsToTopicBoardTests extends TopicBoardsBaseTests {

  private static ValidatableResponse getTopicBoardResponse;
  private static ArrayList<CustomFieldToEdit> existsCustomFields;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавление кастомных полей в доску задач")
  public void editCustomFieldsOfTopicBoardTest() {
    CustomFieldToEditFactory customFieldToEditFactory = new CustomFieldToEditFactory();
    CustomFieldToEdit customFieldToEdit = customFieldToEditFactory
        .createCustomFieldToEditById(customFieldId, IS_ENABLED);

    CustomFieldsToEdit customFieldsToEdit = new CustomFieldsToEdit(customFieldToEdit);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, customFieldsToEdit);

    getTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    responseTopicBoard = getTopicBoardResponse.extract()
        .as(ResponseTopicBoards.class);

    existsCustomFields = responseTopicBoard.getCustomFields();

    assertAll(
        () -> assertEquals(customFieldId, existsCustomFields.get(0).getId())
    );
  }

// комментарии будут удалены позже.
//  todo создать проект, создать кастомные поля в проекте, создать доску задач,
//   получить список кастомных полей в этом проекте, переедать в запросе все кастомные поля,
//   включая те, которые хотим изменить

}
/*

    CustomFieldToEditFactory customFieldToEditFactory = new CustomFieldToEditFactory();
    CustomFieldToEdit customFieldToEdit = customFieldToEditFactory
        .getCustomFieldToEditById(customFieldId, IS_ENABLED);

    CustomFieldsToEdit customFieldsToEdit = new CustomFieldsToEdit(customFieldToEdit);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, customFieldsToEdit);

    getTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    responseTopicBoard = getTopicBoardResponse.extract()
        .as(ResponseTopicBoards.class);

    existsCustomFields = responseTopicBoard.getCustomFields();
 */