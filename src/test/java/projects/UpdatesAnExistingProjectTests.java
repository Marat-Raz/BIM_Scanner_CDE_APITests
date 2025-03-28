package projects;

import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.base.Client;
import dtomodels.project.Project;
import dtomodels.project.ProjectWithConcurrencyStamp;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Редактирование существующего проекта")
public class UpdatesAnExistingProjectTests extends StartTests {

  private static String concurrencyStamp;
  private ValidatableResponse putProjectResponse;
  private ProjectWithConcurrencyStamp projectWithConcurrencyStamp;

  @BeforeEach
  @Step("Создать проект от имени ADMIN")
  public void getConcurrencyStamp() {
    concurrencyStamp = createProjectResponse.extract().path("concurrencyStamp");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить проект пользователя ADMIN")
  public void updatesAnExistingProjectTest() {
    Project newProject = projectFactory.createProject(RANDOM_PROJECT);
    projectWithConcurrencyStamp = new ProjectWithConcurrencyStamp(newProject, concurrencyStamp);
    putProjectResponse = projectsClient.putProjectByItsId(Client.ADMIN_ACCESS_TOKEN, projectId,
        projectWithConcurrencyStamp);
    statusCode = extractStatusCode(putProjectResponse);
    String changedProjectId = putProjectResponse.extract().path("id");

    assertEquals(SC_OK, statusCode);
    assertEquals(projectId, changedProjectId);
  }
}

