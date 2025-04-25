package projects;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import dtomodels.project.ProjectWithConcurrencyStamp;
import dtomodels.project.ResponseProject;
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

  private String concurrencyStamp;
  private ValidatableResponse createProjectResponse;
  private ValidatableResponse putProjectResponse;
  private ProjectWithConcurrencyStamp projectWithConcurrencyStamp;
  private Project testProject;
  private String responsibleId;
  private String testProjectId;

  @BeforeEach
  @Step("Создать проект от имени ADMIN и получить из ответа «concurrencyStamp» и «responsibleId»")
  public void getConcurrencyStamp() {
    testProject = new ProjectFactory().createProject(RANDOM_PROJECT);
    createProjectResponse = projectsClient.createProject(testProject);
    ResponseProject responseProject = createProjectResponse.extract().as(ResponseProject.class);
    concurrencyStamp = responseProject.getConcurrencyStamp();
    testProjectId = responseProject.getId();
    responsibleId = responseProject.getResponsible().getId();
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить проект пользователя ADMIN")
  public void updatesAnExistingProjectTest() {
    Project newProject = projectFactory.createProject(RANDOM_PROJECT);
    projectWithConcurrencyStamp =
        new ProjectWithConcurrencyStamp(newProject, responsibleId, concurrencyStamp);
    putProjectResponse = projectsClient.putProjectByItsId(ADMIN_ACCESS_TOKEN, testProjectId,
        projectWithConcurrencyStamp);
    statusCode = extractStatusCode(putProjectResponse);
    String changedProjectId = putProjectResponse.extract().path("id");

    assertEquals(SC_OK, statusCode);
    assertEquals(testProjectId, changedProjectId);
  }
}

