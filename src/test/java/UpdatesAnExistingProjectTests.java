import static models.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.ProjectsClient;
import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectFactory;
import models.project.ProjectWithConcurrencyStamp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdatesAnExistingProjectTests extends StartTests {

  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ValidatableResponse createProjectResponse;
  private static String projectId;
  private static String concurrencyStamp;
  private ValidatableResponse putProjectResponse;
  ProjectWithConcurrencyStamp projectWithConcurrencyStamp;

  @BeforeAll
  @Step("Создать проект от имени ADMIN")
  public static void createProject() {
    Project project = projectFactory.createProject(RANDOM_PROJECT);
    createProjectResponse = projectsClient.createProject(Client.ADMIN_ACCESS_TOKEN, project);
    projectId = createProjectResponse.extract().path("id");
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

