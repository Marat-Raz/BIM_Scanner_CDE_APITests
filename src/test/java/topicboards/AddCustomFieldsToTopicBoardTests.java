package topicboards;

import static models.customfields.CustomFieldType.TEXT;
import static models.customfields.customfieldstoedit.CustomFieldToEditType.IS_ENABLED;
import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.CustomFieldsClient;
import client.ProjectsClient;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import models.customfields.CustomField;
import models.customfields.CustomFieldFactory;
import models.customfields.ResponseCustomField;
import models.customfields.customfieldstoedit.CustomFieldToEdit;
import models.customfields.customfieldstoedit.CustomFieldToEditFactory;
import models.customfields.customfieldstoedit.CustomFieldsToEdit;
import models.project.Project;
import models.project.ProjectFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddCustomFieldsToTopicBoardTests extends StartTests {

  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static String projectId;
  private static ValidatableResponse addCustomFieldResponse;
  private static ValidatableResponse editCustomFieldResponse;
  private static CustomField customField;
  private static String customFieldId;
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;

  @BeforeAll
  @Step("Создать проект, добавить кастомные поля в проект")
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
    customField = new CustomFieldFactory().createCustomField(TEXT);
    addCustomFieldResponse = customFieldsClient.addNewCustomFieldToProject(projectId, customField);
    customFieldId = addCustomFieldResponse.extract().path("id");
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    topicBoardId = createTopicBoardsResponse.extract().path("id");
  }

  @AfterAll
  public static void deleteProject() {
    projectsClient.deleteProjectByItsId(projectId);
  }

  @Test
  @DisplayName("Добавление кастомных полей в доску задач")
  public void editCustomFieldsOfTopicBoardTest() {
    CustomFieldToEditFactory customFieldToEditFactory = new CustomFieldToEditFactory();
    CustomFieldToEdit customFieldToEdit = customFieldToEditFactory
        .getCustomFieldToEditById(customFieldId, IS_ENABLED);

    CustomFieldsToEdit customFieldsToEdit = new CustomFieldsToEdit(customFieldToEdit);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, customFieldsToEdit);
    ResponseTopicBoards responseTopicBoards = editCustomFieldResponse.extract().as(
        ResponseTopicBoards.class);

    ArrayList<CustomFieldToEdit> existsCustomField = responseTopicBoards.getCustomFields();
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
