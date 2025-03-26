package projects;

import static constants.CommonConstants.PNG_FILE;
import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.RestAssuredLogging;
import basetests.StartTests;
import client.ProjectsClient;
import client.base.Client;
import dtomodels.PaginatedResponse;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import dtomodels.project.ResponseProject;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import org.junit.jupiter.api.*;

public class SetProjectCoverImageTests extends StartTests {
// todo https://software-testing.ru/library/testing/testing-for-beginners/3318-six-tips-and-four-tools-for-file-upload

  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ValidatableResponse createProjectResponse;
  private static String projectId;
  private static ValidatableResponse getAllProjectResponse;
  private static ValidatableResponse deleteProjectResponse;
  private ValidatableResponse setIconResponse;

  @BeforeAll
  @Step("Создать проект от имени ADMIN")
  public static void createProject() {// todo перенести в ProjectBaseTest
    Project project = projectFactory.createProject(RANDOM_PROJECT);
    createProjectResponse = projectsClient.createProject(Client.ADMIN_ACCESS_TOKEN, project);
    projectId = createProjectResponse.extract().path("id");
  }

  @BeforeEach
  public void setupMinimalLogging() {
    RestAssuredLogging.setupMinimalLogging();
  }

  @AfterEach
  void restoreOriginalFilters() {
    RestAssuredLogging.restoreOriginalFilters();
  }

  @AfterAll
  @Step("Получить все проекты в системе и удалить все проекты всех пользователей после тестов")
  public static void deleteAllProjects() {// todo перенести в ProjectBaseTest
    getAllProjectResponse = projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    PaginatedResponse<ResponseProject> projectPaginatedResponse =
        getAllProjectResponse.extract().body().as(typeRef);
    for (ResponseProject project : projectPaginatedResponse.getItems()) {
      deleteProjectResponse = projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Задать изображение обложки проекта")
  public void setProjectCoverImageTest() {
    setIconResponse = projectsClient.setProjectCoverImage(projectId, new File(PNG_FILE));
    statusCode = extractStatusCode(setIconResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
