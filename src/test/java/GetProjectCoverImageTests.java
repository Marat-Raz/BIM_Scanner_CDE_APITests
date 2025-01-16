import static models.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.ProjectsClient;
import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
  private String pathToDownload = "src/main/resources/download";
  private String fileName = "coverImage.txt";

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

    // todo нужно проверить папку на отсутствие идентичного файла и удалить, если имеется
    if (statusCode == 200) {
      File outputFile  = new File(pathToDownload, fileName);
      if(outputFile .exists()) {
        outputFile .delete();
      }
      byte[] image = getIconResponse.extract().body().asByteArray();
      OutputStream outStream = null;
      try {
        while (true) {
          outStream = new FileOutputStream(outputFile);
          outStream.write(image);
          outStream.flush();
        }
      } catch (Exception e) {
        System.out.println("Error writing file " + outputFile.getAbsolutePath());
      } finally {
        if (outStream != null) {
          outStream.close();
        }

        // fixme при запуске тестов через maven работает не корректно

        assertEquals(SC_OK, statusCode);
        assertTrue(outputFile.exists());

// todo как проверить на соответствие загруженный и выгруженный файлы?
      }
    }
  }

}
