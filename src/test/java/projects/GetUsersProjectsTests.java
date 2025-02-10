package projects;

import static models.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.ProjectsClient;
import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import models.project.Project;
import models.project.ProjectFactory;
import models.project.ServerResponseProject;
import org.junit.jupiter.api.*;

public class GetUsersProjectsTests extends StartTests {

  private static ValidatableResponse getAllProjectResponse;
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ArrayList<Project> projectList = new ArrayList<Project>();
  private static List<ServerResponseProject> serverResponseProjectList = new ArrayList<>();
  private static ValidatableResponse deleteProjectResponse;
  private static int numberOfProjects = 5;

  @BeforeAll
  @Step("Создать несколько проектов от имени ADMIN")
  public static void createProjects() { // todo рассмотреть вынос этого метода в Steps
    for (int i = 0; i < numberOfProjects; i++) {
      projectList.add(projectFactory.createProject(RANDOM_PROJECT));
    }
    for (Project project : projectList) {
      projectsClient.createProject(Client.ADMIN_ACCESS_TOKEN, project);
    }
  }

  @AfterAll // todo можно ли вынести этот метод в startTests.StartTests.cleanData?
  @Step("Получить все проекты в системе и удалить все проекты всех пользователей после тестов")
  public static void deleteAllProjects() {
    getAllProjectResponse = projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    serverResponseProjectList = List.of(getAllProjectResponse.extract().body()
        .as(ServerResponseProject[].class));
    for (ServerResponseProject project : serverResponseProjectList) {
      deleteProjectResponse = projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список проектов для ADMIN")
    public void getProjectsForAdminTest() {
    getAllProjectResponse = projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    statusCode = extractStatusCode(getAllProjectResponse);

    assertEquals(SC_OK, statusCode);
  }

  // todo проверить список проектов со списком проектов в ответе
  // + реализовать остальные проверки для query options
}

