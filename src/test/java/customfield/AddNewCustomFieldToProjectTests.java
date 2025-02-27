package customfield;

import static models.customfields.CustomFieldType.ENUMERATION;
import static models.project.ProjectType.DEFAULT_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import baseTests.StartTests;
import client.CustomFieldsClient;
import client.ProjectsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.customfields.CustomField;
import models.customfields.CustomFieldFactory;
import models.customfields.ResponseCustomField;
import models.customfields.enumerationitem.EnumerationItem;
import models.customfields.enumerationitem.ResponseEnumerationItem;
import models.project.Project;
import models.project.ProjectFactory;
import org.junit.jupiter.api.*;

public class AddNewCustomFieldToProjectTests extends StartTests {

  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  private static String projectId;
  private ValidatableResponse addResponse;
  private CustomField customField;

  @BeforeAll
  @Step("Создать проект")
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
  }

  @AfterAll
  public static void deleteProject() {
    projectsClient.deleteProjectByItsId(projectId);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавить новое кастомное поле в проект")
  public void createTopicBoardsGroupTest() {
    customField = new CustomFieldFactory().createCustomField(ENUMERATION);
    addResponse = customFieldsClient.addNewCustomFieldToProject(projectId, customField);
    statusCode = extractStatusCode(addResponse);
    ResponseCustomField responseCustomField = addResponse.extract().as(ResponseCustomField.class);
    ArrayList<ResponseEnumerationItem> responseEnumerationItems = responseCustomField.getEnumerationItems();

    List<EnumerationItem> enumerationItems = customField.getEnumerationItems();
    List<String> expectedEnumerationItemName = new ArrayList<>();
    for (EnumerationItem enumerationItem : enumerationItems) {
      expectedEnumerationItemName.add(enumerationItem.getName());
    }

    List<String> actualEnumerationItemName = new ArrayList<>();
    for (ResponseEnumerationItem enumerationItem : responseEnumerationItems) {
      actualEnumerationItemName.add(enumerationItem.getName());
    }

    Collections.sort(expectedEnumerationItemName);
    Collections.sort(actualEnumerationItemName);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(responseCustomField.getType(),
            customField.getType().toString().toLowerCase()),
        () -> assertEquals(responseCustomField.getName(), customField.getName()),
        () -> assertTrue(expectedEnumerationItemName.equals(actualEnumerationItemName))

    );
  }
  // todo реализовать получение id кастомных полей

}
