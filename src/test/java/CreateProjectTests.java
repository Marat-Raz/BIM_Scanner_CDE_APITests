import static models.project.ProjectType.DEFAULT_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.ProjectsClient;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateProjectTests extends StartTests {
  ValidatableResponse createProjectResponse;
  ProjectsClient projectsClient;
  ProjectFactory projectFactory = new ProjectFactory();

/*
// todo создать проект со всеми параметрами
создать проект без обязательного поля
создать проект без необязательных полей
создать проект с null
создать проект с неверной авторизацией
создать проект с не верными данными: не верные данные в обязательных полях
проверить длину строк параметров проекта - создать проекта с проверкой длины строки его параметров
 */

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить проект по id")
  public void getUserByIdTest() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    createProjectResponse = projectsClient.createProject(project);
    String projectId = createProjectResponse.extract().path("id");

    statusCode = extractStatusCode(createProjectResponse);

    assertEquals(SC_OK, statusCode);

  }

}
