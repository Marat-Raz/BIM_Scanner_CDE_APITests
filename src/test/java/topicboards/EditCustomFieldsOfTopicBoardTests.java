package topicboards;

import static dtomodels.customfields.customfieldsintopicbords.CustomFieldOnTopicBoardsType.IS_ENABLED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.customfields.customfieldsintopicbords.CustomFieldOnTopicBoards;
import dtomodels.customfields.customfieldsintopicbords.CustomFieldOnTopicBoardsFactory;
import dtomodels.customfields.customfieldsintopicbords.CustomFieldsOnTopicBoards;
import dtomodels.topicboards.ResponseTopicBoards;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EditCustomFieldsOfTopicBoardTests extends TopicBoardsBaseTests {

  private static ValidatableResponse editCustomFieldResponse;
  private static ValidatableResponse getTopicBoardResponse;
  private static ArrayList<CustomFieldOnTopicBoards> existsCustomFields = new ArrayList<>();

  @BeforeEach
  @Step("Добавить кастомные поля в доску задач")
  public void createProject() {
    CustomFieldOnTopicBoardsFactory customFieldOnTopicBoardsFactory = new CustomFieldOnTopicBoardsFactory();
    CustomFieldOnTopicBoards customFieldOnTopicBoards = customFieldOnTopicBoardsFactory
        .createCustomFieldOnTopicBoardsById(customFieldId, IS_ENABLED);

    CustomFieldsOnTopicBoards customFieldsOnTopicBoards = new CustomFieldsOnTopicBoards(
        customFieldOnTopicBoards);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, customFieldsOnTopicBoards);

    getTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    responseTopicBoard = getTopicBoardResponse.extract()
        .as(ResponseTopicBoards.class);

    existsCustomFields = responseTopicBoard.getCustomFields();

  }

  @Test
  @DisplayName("Редактирование кастомных полей в доске задач")
  public void editCustomFieldsOfTopicBoardTest() {
    CustomFieldOnTopicBoards editableCustomField = existsCustomFields.get(0);
    String expectedTxt = "new Test Value";
    editableCustomField.setDefaultValue(expectedTxt);

    existsCustomFields.set(0, editableCustomField);
    CustomFieldsOnTopicBoards customFieldsOnTopicBoards = new CustomFieldsOnTopicBoards(
        existsCustomFields);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, customFieldsOnTopicBoards);
    ResponseTopicBoards responseTopicBoards = editCustomFieldResponse.extract()
        .as(ResponseTopicBoards.class);

    ArrayList<CustomFieldOnTopicBoards> actualCustomField = responseTopicBoards.getCustomFields();

    assertAll(
        () -> assertEquals(expectedTxt, actualCustomField.get(0).getDefaultValue())
    );

  }

// комментарии будут удалены позже.
// todo создать проект, создать кастомные поля в проекте, создать доску задач,
//  получить список кастомных полей в этом проекте, переедать в запросе все кастомные поля,
//  включая те, которые хотим изменить

}
