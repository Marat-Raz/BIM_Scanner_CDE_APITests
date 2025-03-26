package projects;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static client.base.Client.BASE_URL;
import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.RestAssuredLogging;
import basetests.StartTests;
import client.ProjectsClient;
import dtomodels.PaginatedResponse;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import dtomodels.project.ResponseProject;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.io.FileOutputStream;
import org.junit.jupiter.api.*;

public class GetProjectCoverImageTests extends StartTests {

  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ValidatableResponse createProjectResponse;
  private static String projectId;
  private static ValidatableResponse getAllProjectResponse;
  private String pathToDownload = "src/main/resources/download";
  private String fileName = "coverImage.png";

  @BeforeAll
  @Step("Создать проект от имени ADMIN")
  public static void createProject() { // todo перенести в ProjectBaseTest
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

  @AfterAll
  @Step("Получить все проекты в системе и удалить все проекты всех пользователей после тестов")
  public static void deleteAllProjects() {// todo перенести в ProjectBaseTest
    getAllProjectResponse = projectsClient.getListOfProjects(ADMIN_ACCESS_TOKEN);
    PaginatedResponse<ResponseProject> projectPaginatedResponse =
        getAllProjectResponse.extract().body().as(typeRef);
    for (ResponseProject project : projectPaginatedResponse.getItems()) {
      projectsClient.deleteProjectByItsId(ADMIN_ACCESS_TOKEN, project.getId());
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить изображение обложки проекта")
  public void getCoverImageOfProjectTest() {
    //projectsClient.setProjectCoverImage(projectId);
    // todo перенести код ниже в ProjectsClient
    // fixme
    var response = RestAssured.given().auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(BASE_URL + "api/projects/" + projectId + "/cover")
        .then();
    statusCode = extractStatusCode(response);

    // todo нужно проверить папку на отсутствие идентичного файла и удалить, если имеется
    byte[] dowloadedFile = response.extract().asByteArray();
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(new File(pathToDownload, fileName));
      fileOutputStream.write(dowloadedFile);
      fileOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals(SC_OK, statusCode);
  }

}
