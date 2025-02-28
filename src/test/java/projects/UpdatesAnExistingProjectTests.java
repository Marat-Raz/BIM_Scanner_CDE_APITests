package projects;

import static models.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectWithConcurrencyStamp;
import org.junit.jupiter.api.*;

public class UpdatesAnExistingProjectTests extends StartTests {

  private static String concurrencyStamp;
  private ValidatableResponse putProjectResponse;
  ProjectWithConcurrencyStamp projectWithConcurrencyStamp;

  @BeforeEach
  @Step("Создать проект от имени ADMIN")
  public void getConcurrencyStamp() {
    concurrencyStamp = createProjectResponse.extract().path("concurrencyStamp");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить проект пользователя ADMIN")
  public void updatesAnExistingProjectTest() {
    Project newProject = projectFactory.createProject(RANDOM_PROJECT);
    projectWithConcurrencyStamp = new ProjectWithConcurrencyStamp(newProject, concurrencyStamp);
    putProjectResponse = projectsClient.putProjectByItsId(Client.ADMIN_ACCESS_TOKEN, projectId,
        projectWithConcurrencyStamp);
    statusCode = extractStatusCode(putProjectResponse);
    String changedProjectId = putProjectResponse.extract().path("id");

    assertEquals(SC_OK, statusCode);
    assertEquals(projectId, changedProjectId);
  }
}

