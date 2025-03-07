package projects;

import static models.project.ProjectType.PROJECT_WITHOUT_DATA;
import static models.project.ProjectType.PROJECT_WITHOUT_NAME;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.ProjectsClient;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateProjectTests extends StartTests {

  Project project;
/*
// todo создать проект со всеми параметрами
// todo проверить создание проекта!
// todo создать проект без необязательных полей
// todo создать проект с неверной авторизацией
// todo создать проект с не верными данными: не верные данные в обязательных полях - параметризованные тесты
// todo проверить длину строк параметров проекта - создать проекта с проверкой длины строки его параметров
 */

  @Test
  @DisplayName("Создать проект без названия, без обязательного поля name")
  public void createProjectWithoutNameTest() {
    project = projectFactory.createProject(PROJECT_WITHOUT_NAME);
    project.setResponsibleId(userId);
    createProjectResponse = projectsClient.createProject(project);
    statusCode = extractStatusCode(createProjectResponse);

    assertEquals(SC_UNPROCESSABLE_ENTITY, statusCode);
  }

  @Test
  @DisplayName("Создать проект, где все параметры null")
  public void createProjectWithNullTest() {
    project = projectFactory.createProject(PROJECT_WITHOUT_DATA);
    project.setResponsibleId(userId);
    createProjectResponse = projectsClient.createProject(project);
    statusCode = extractStatusCode(createProjectResponse);

    assertEquals(SC_UNPROCESSABLE_ENTITY, statusCode);
  }

}
