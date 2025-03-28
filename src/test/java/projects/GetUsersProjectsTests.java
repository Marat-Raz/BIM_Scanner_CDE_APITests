package projects;

import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.ProjectsClient;
import client.base.Client;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Получение проектов пользователя")
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
      projectsClient.createProject(Client.ADMIN_ACCESS_TOKEN, project);
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

