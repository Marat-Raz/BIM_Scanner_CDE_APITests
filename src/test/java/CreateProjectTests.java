import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.project.ProjectType.PROJECT_WITHOUT_NAME;
import static models.project.ProjectType.PROJECT_WITHOUT_DATA;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
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

  private ValidatableResponse createProjectResponse;
  private ProjectsClient projectsClient = new ProjectsClient();
  private ProjectFactory projectFactory = new ProjectFactory();

/*
// todo создать проект со всеми параметрами
// todo создать проект без необязательных полей
// todo создать проект с неверной авторизацией
// todo создать проект с не верными данными: не верные данные в обязательных полях - параметризованные тесты
// todo проверить длину строк параметров проекта - создать проекта с проверкой длины строки его параметров
 */

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать проект")
  public void createProjectTest() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    createProjectResponse = projectsClient.createProject(project);
    String actualProjectName = createProjectResponse.extract().path("name");
    statusCode = extractStatusCode(createProjectResponse);

    assertEquals(SC_OK, statusCode);
    assertEquals(project.getName(), actualProjectName);
  }

  @Test
  @DisplayName("Создать проект без названия, без обязательного поля name")
  public void createProjectWithoutNameTest() {
    Project project = projectFactory.createProject(PROJECT_WITHOUT_NAME);
    project.setResponsibleId(userId);
    createProjectResponse = projectsClient.createProject(project);
    statusCode = extractStatusCode(createProjectResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
  }

  @Test
  @DisplayName("Создать проект, где все параметры null")
  public void createProjectWithNullTest() {
    Project project = projectFactory.createProject(PROJECT_WITHOUT_DATA);
    project.setResponsibleId(userId);
    createProjectResponse = projectsClient.createProject(project);
    statusCode = extractStatusCode(createProjectResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
  }

}
