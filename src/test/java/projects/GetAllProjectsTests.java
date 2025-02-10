package projects;

import static models.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.ProjectsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import models.project.Project;
import models.project.ProjectFactory;
import org.junit.jupiter.api.*;

public class GetAllProjectsTests extends StartTests {

  private ValidatableResponse getAllProjectResponse;
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ArrayList<Project> projectList = new ArrayList<Project>();
  private static int numberOfProjects = 5;

  @BeforeAll
  @Step("Создать несколько проектов для теста")
  public static void createProjects() {
    for (int i = 0; i < numberOfProjects; i++) {
      projectList.add(projectFactory.createProject(RANDOM_PROJECT));
    }
    for (Project project : projectList) {
      projectsClient.createProject(project);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список проектов")
  public void getAllProjectTest() {
    getAllProjectResponse = projectsClient.getListOfProjectsForAdmin();
    statusCode = extractStatusCode(getAllProjectResponse);

    assertEquals(SC_OK, statusCode);
  }

  // todo проверить список проектов со списком проектов в ответе
  // + реализовать остальные проверки для query options
}

