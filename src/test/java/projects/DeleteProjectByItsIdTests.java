package projects;

import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.base.Client;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import dtomodels.project.ResponseFromGetAllProjects;
import dtomodels.project.ServerResponseProject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Удаление проекта")
public class DeleteProjectByItsIdTests extends StartTests {

  private ValidatableResponse getAllProjectResponse;
  private ValidatableResponse deleteProjectResponse;
  private static ArrayList<Project> projectList = new ArrayList<Project>();
  private static List<ServerResponseProject> serverResponseProjectList = new ArrayList<>();
  private static int numberOfProjects = 5;

  @BeforeAll
  @Step("Создать проекты от имени ADMIN")
  public static void createProject() { // todo рассмотреть вынос этого метода в Steps
    for (int i = 0; i < numberOfProjects; i++) {
      projectList.add(new ProjectFactory().createProject(RANDOM_PROJECT));
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
    serverResponseProjectList = getAllProjectResponse.extract().body()
        .as(ResponseFromGetAllProjects.class).getItems();
    for (ServerResponseProject project : serverResponseProjectList) {
      deleteProjectResponse = projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());
      statusCode = extractStatusCode(deleteProjectResponse);

      assertEquals(SC_NO_CONTENT, statusCode);
    }
  }

}
