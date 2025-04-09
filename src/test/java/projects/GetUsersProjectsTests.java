package projects;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.ProjectsClient;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetUsersProjectsTests extends StartTests {

  private static ValidatableResponse getAllProjectResponse;
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ArrayList<Project> projectList = new ArrayList<Project>();
  private static int numberOfProjects = 5;

  @BeforeAll
  @Step("Создать несколько проектов от имени ADMIN")
  public static void createProjects() { // todo рассмотреть вынос этого метода в Steps
    for (int i = 0; i < numberOfProjects; i++) {
      projectList.add(new ProjectFactory().createProject(RANDOM_PROJECT));
    }
    for (Project project : projectList) {
      projectsClient.createProject(ADMIN_ACCESS_TOKEN, project);
    }
  }


  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список проектов для ADMIN")
  public void getProjectsForAdminTest() {
    getAllProjectResponse = projectsClient.getListOfProjects(ADMIN_ACCESS_TOKEN);
    statusCode = extractStatusCode(getAllProjectResponse);

    assertEquals(SC_OK, statusCode);
  }

  // todo проверить список проектов со списком проектов в ответе
  // + реализовать остальные проверки для query options
}

