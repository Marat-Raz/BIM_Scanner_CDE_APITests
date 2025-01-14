import static models.project.ProjectType.RANDOM_PROJECT;
import static models.user.UserType.DEFAULT_USER;
import static models.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.ProjectsClient;
import client.TokenClient;
import client.UserClient;
import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import models.project.Project;
import models.project.ProjectFactory;
import models.token.TokenBuilder;
import models.user.User;
import models.user.UserFactory;
import org.junit.jupiter.api.*;

public class GetUsersProjectsTests extends StartTests {

  private ValidatableResponse getAllProjectResponse;
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ArrayList<Project> projectList = new ArrayList<Project>();
  private static int numberOfProjects = 5;

  @BeforeAll
  @Step("Создать несколько проектов от имени ADMIN")
  public static void createProjects() {
    for (int i = 0; i < numberOfProjects; i++) {
      projectList.add(projectFactory.createProject(RANDOM_PROJECT));
    }
    for (Project project : projectList) {
      projectsClient.createProject(Client.ADMIN_ACCESS_TOKEN, project);
    }
  }

  @AfterAll
  public static void deleteAllProjects() {
// todo получить все проекты в системе и удалить все проекты всех пользователей после тестов

/*    for (Project project : projectList) {
      projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN, project);
    }*/
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список проектов для ADMIN")
  public void getUserByIdTest() {
    getAllProjectResponse = projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    statusCode = extractStatusCode(getAllProjectResponse);

    assertEquals(SC_OK, statusCode);
  }

  // todo проверить список проектов со списком проектов в ответе
  // + реализовать остальные проверки для query options
}

