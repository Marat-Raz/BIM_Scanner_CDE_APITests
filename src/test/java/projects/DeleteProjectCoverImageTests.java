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
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

public class DeleteProjectCoverImageTests extends StartTests {

  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ValidatableResponse createProjectResponse;
  private static String projectId;
  private ValidatableResponse deleteIconResponse;

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
      System.out.println("Фильтры изменены перед тестом, где происходит работа с файлами.\n"
          + "Логи тестов не выводятся для стабильности работы тестов");
    });
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить изображение обложки проекта")
  public void getProjectCoverImageTest() {
    projectsClient.setProjectCoverImage(projectId);
    deleteIconResponse = projectsClient.deleteProjectCoverImage(projectId);
    statusCode = extractStatusCode(deleteIconResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }
}

