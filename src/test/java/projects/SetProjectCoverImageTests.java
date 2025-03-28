package projects;

import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.RestAssuredFilterSwitcher;
import basetests.StartTests;
import client.ProjectsClient;
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
import org.junit.jupiter.api.*;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Установить обложку проекта")
public class SetProjectCoverImageTests extends StartTests {
// todo https://software-testing.ru/library/testing/testing-for-beginners/3318-six-tips-and-four-tools-for-file-upload

  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ValidatableResponse createProjectResponse;
  private static String projectId;
  private static ValidatableResponse getAllProjectResponse;
  private static List<ServerResponseProject> serverResponseProjectList = new ArrayList<>();
  private static ValidatableResponse deleteProjectResponse;
  private ValidatableResponse setIconResponse;

  @BeforeAll
  @Step("Создать проект от имени ADMIN")
  public static void createProject() {
    Project project = projectFactory.createProject(RANDOM_PROJECT);
    createProjectResponse = projectsClient.createProject(Client.ADMIN_ACCESS_TOKEN, project);
    projectId = createProjectResponse.extract().path("id");
  }

  @BeforeEach
  public void setUp() {
    RestAssuredFilterSwitcher.withTemporaryFilters(() -> {
      System.out.println("Фильтры изменены перед тестом c передачей файлов по api.");
    });
  }

  @AfterAll
  @Step("Получить все проекты в системе и удалить все проекты всех пользователей после тестов")
  public static void deleteAllProjects() {
    getAllProjectResponse = projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    serverResponseProjectList = getAllProjectResponse.extract().body()
        .as(ResponseFromGetAllProjects.class).getItems();
    for (ServerResponseProject project : serverResponseProjectList) {
      deleteProjectResponse = projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Задать изображение обложки проекта")
  public void setProjectCoverImageTest() {
    setIconResponse = projectsClient.setProjectCoverImage(projectId);
    statusCode = extractStatusCode(setIconResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
