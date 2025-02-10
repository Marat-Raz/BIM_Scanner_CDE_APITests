package projects;

import static models.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
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

public class DeleteProjectByItsIdTests extends StartTests {

  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private ValidatableResponse getAllProjectResponse;
  private ValidatableResponse deleteProjectResponse;
  private static ArrayList<Project> projectList = new ArrayList<Project>();
  private static List<ServerResponseProject> serverResponseProjectList = new ArrayList<>();


  @BeforeAll
  @Step("Создать проекты от имени ADMIN")
  public static void createProject() { // todo рассмотреть вынос этого метода в Steps
    for (int i = 0; i < 5; i++) {
      projectList.add(projectFactory.createProject(RANDOM_PROJECT));
    }
    for (Project project : projectList) {
      projectsClient.createProject(Client.ADMIN_ACCESS_TOKEN, project);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить все проекты пользователя ADMIN")
  public void deleteProjectByItsIdTest() {
    getAllProjectResponse = projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    serverResponseProjectList = List.of(getAllProjectResponse.extract().body()
        .as(ServerResponseProject[].class));
    for (ServerResponseProject project : serverResponseProjectList) {
      deleteProjectResponse = projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());
      statusCode = extractStatusCode(deleteProjectResponse);

      assertEquals(SC_NO_CONTENT, statusCode);
    }
  }

}
