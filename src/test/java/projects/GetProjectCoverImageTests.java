package projects;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static client.base.Client.BASE_URL;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import models.project.ServerResponseProject;
import org.junit.jupiter.api.*;

public class GetProjectCoverImageTests extends StartTests {

  private static List<ServerResponseProject> serverResponseProjectList = new ArrayList<>();
  private String pathToDownload = "src/main/resources/download";
  private String fileName = "coverImage.png";

  @BeforeEach
  @Step("Установить обложку проекта")
  public void setProjectCoverImage() {
    projectsClient.setProjectCoverImage(projectId);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить изображение обложки проекта")
  public void getCoverImageOfProjectTest() {
    // todo перенести код ниже в ProjectsClient
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
