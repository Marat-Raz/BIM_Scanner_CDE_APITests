package topicboards;

import static models.customfields.customfieldstoedit.CustomFieldToEditType.IS_ENABLED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import models.customfields.customfieldstoedit.CustomFieldToEdit;
import models.customfields.customfieldstoedit.CustomFieldToEditFactory;
import models.customfields.customfieldstoedit.CustomFieldsToEdit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddCustomFieldsToTopicBoardTests extends TopicBoardsBaseTests {

  @Test
  @DisplayName("Добавление кастомных полей в доску задач")
  public void editCustomFieldsOfTopicBoardTest() {
    CustomFieldToEditFactory customFieldToEditFactory = new CustomFieldToEditFactory();
    CustomFieldToEdit customFieldToEdit = customFieldToEditFactory
        .getCustomFieldToEditById(customFieldId, IS_ENABLED);

    CustomFieldsToEdit customFieldsToEdit = new CustomFieldsToEdit(customFieldToEdit);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, customFieldsToEdit);

    ArrayList<CustomFieldToEdit> existsCustomField = responseTopicBoards.getCustomFields();
    // fixme переделать!
    String actualCustomFieldId = existsCustomField.get(0).getId();

    assertAll(
        () -> assertEquals(customFieldId, actualCustomFieldId)
    );
  }

// комментарии будут удалены позже.
//  todo создать проект, создать кастомные поля в проекте, создать доску задач,
//   получить список кастомных полей в этом проекте, переедать в запросе все кастомные поля,
//   включая те, которые хотим изменить

}
