package projects;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static constants.CommonConstants.PNG_FILE;
import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.RestAssuredLogging;
import basetests.StartTests;
import client.ProjectsClient;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import org.junit.jupiter.api.*;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Удаление обложки проекта")
public class DeleteProjectCoverImageTests extends StartTests {

  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ValidatableResponse createProjectResponse;
  private static String projectId;
  private ValidatableResponse deleteIconResponse;

  @BeforeAll
  @Step("Создать проект от имени ADMIN")
  public static void createProject() {// todo перенести в ProjectBaseTest
    Project project = projectFactory.createProject(RANDOM_PROJECT);
    createProjectResponse = projectsClient.createProject(ADMIN_ACCESS_TOKEN, project);
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

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить изображение обложки проекта")
  public void getProjectCoverImageTest() {
    projectsClient.setProjectCoverImage(projectId, new File(PNG_FILE));
    deleteIconResponse = projectsClient.deleteProjectCoverImage(projectId);
    statusCode = extractStatusCode(deleteIconResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}

