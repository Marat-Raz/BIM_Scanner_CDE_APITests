package projects;

import static models.project.ProjectType.DEFAULT_PROJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.ProjectsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetProjectByItsIdTests extends StartTests {

  private ValidatableResponse getProjectResponse;
  private ValidatableResponse createProjectResponse;
  private ProjectFactory projectFactory = new ProjectFactory();
  private ProjectsClient projectsClient = new ProjectsClient();
  private String projectId;
  private String requestProjectId;

  @BeforeEach
  @Step("Создать проект для теста")
  public void createProjectForTests() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить проект по id")
  public void getProjectByIdTest() {
    getProjectResponse = projectsClient.getProjectByItsIdForAdmin(projectId);
    requestProjectId = getProjectResponse.extract().path("id");

    assertEquals(requestProjectId, requestProjectId);
  }

}
