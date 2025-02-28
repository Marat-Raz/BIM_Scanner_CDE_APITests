package topicboards;

import static models.customfields.CustomFieldType.TEXT;
import static models.customfields.customfieldstoedit.CustomFieldToEditType.IS_ENABLED;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.CustomFieldsClient;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import models.customfields.CustomField;
import models.customfields.CustomFieldFactory;
import models.customfields.customfieldstoedit.CustomFieldToEdit;
import models.customfields.customfieldstoedit.CustomFieldToEditFactory;
import models.customfields.customfieldstoedit.CustomFieldsToEdit;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EditCustomFieldsOfTopicBoardTests extends StartTests {

  private static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static ValidatableResponse addCustomFieldResponse;
  private static ValidatableResponse editCustomFieldResponse;
  private static CustomField customField;
  private static String customFieldId;
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse getTopicBoardResponse;
  private static ResponseTopicBoards responseTopicBoard;
  private static ArrayList<CustomFieldToEdit> existsCustomFields;

  @BeforeAll
  @Step("Добавить кастомные поля в проект")
  public static void createProject() {
    customField = new CustomFieldFactory().createCustomField(TEXT);
    addCustomFieldResponse = customFieldsClient.addNewCustomFieldToProject(projectId, customField);
    customFieldId = addCustomFieldResponse.extract().path("id");

    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    topicBoardId = createTopicBoardsResponse.extract().path("id");

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

  }

  @Test
  @DisplayName("Редактирование кастомных полей в доске задач")
  public void editCustomFieldsOfTopicBoardTest() {
    CustomFieldToEdit editableCustomField = existsCustomFields.get(0);
    String expectedTxt = "new Test Value";
    editableCustomField.setDefaultValue(expectedTxt);

    existsCustomFields.set(0, editableCustomField);
    CustomFieldsToEdit customFieldsToEdit = new CustomFieldsToEdit(existsCustomFields);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, customFieldsToEdit);
    ResponseTopicBoards responseTopicBoards = editCustomFieldResponse.extract()
        .as(ResponseTopicBoards.class);

    ArrayList<CustomFieldToEdit> actualCustomField = responseTopicBoards.getCustomFields();

    assertAll(
        () -> assertEquals(expectedTxt, actualCustomField.get(0).getDefaultValue())
    );

  }

// комментарии будут удалены позже.
// todo создать проект, создать кастомные поля в проекте, создать доску задач,
//  получить список кастомных полей в этом проекте, переедать в запросе все кастомные поля,
//  включая те, которые хотим изменить

}
