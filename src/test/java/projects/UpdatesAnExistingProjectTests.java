package projects;

import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dtomodels.project.Project;
import dtomodels.project.ProjectWithConcurrencyStamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdatesAnExistingProjectTests extends StartTests {

  private static String concurrencyStamp;
  private ValidatableResponse putProjectResponse;
  private ProjectWithConcurrencyStamp projectWithConcurrencyStamp;

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

