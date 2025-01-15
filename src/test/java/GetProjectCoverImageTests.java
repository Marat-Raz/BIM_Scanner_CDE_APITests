import static models.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.ProjectsClient;
import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import models.project.Project;
import models.project.ProjectFactory;
import models.project.ServerResponseProject;
import org.junit.jupiter.api.*;

public class GetProjectCoverImageTests extends StartTests {

  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ValidatableResponse createProjectResponse;
  private static String projectId;
  private static ValidatableResponse getAllProjectResponse;
  private static List<ServerResponseProject> serverResponseProjectList = new ArrayList<>();
  private static ValidatableResponse deleteProjectResponse;
  private ValidatableResponse getIconResponse;
  private String path = "src/main/resources/coverImage.png";

  @BeforeAll
  @Step("Создать проект от имени ADMIN")
  public static void createProject() {
    Project project = projectFactory.createProject(RANDOM_PROJECT);
    createProjectResponse = projectsClient.createProject(Client.ADMIN_ACCESS_TOKEN, project);
    projectId = createProjectResponse.extract().path("id");
  }

  @AfterAll
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
  @DisplayName("Получить изображение обложки проекта")
  public void getProjectCoverImageTest() throws IOException {
    projectsClient.setProjectCoverImage(projectId);
    getIconResponse = projectsClient.getProjectCoverImage(projectId);
    statusCode = extractStatusCode(getIconResponse);

    byte[] image = getIconResponse.extract().body().asByteArray();
    OutputStream outStream = new FileOutputStream(path);
    File file = new File(path);
    outStream.write(image);
    outStream.close();

    assertEquals(SC_OK, statusCode);
    assertTrue(file.exists());

// todo как проверить на соответствие загруженный и выгруженный файлы?
  }
}
