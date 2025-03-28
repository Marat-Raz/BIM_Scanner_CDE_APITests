package projects;

import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Получение всех проектов")
public class GetAllProjectsTests extends StartTests {

  private ValidatableResponse getAllProjectResponse;
  private static ArrayList<Project> projectList = new ArrayList<Project>();
  private static int numberOfProjects = 5;

  @BeforeAll
  @Step("Создать несколько проектов для теста")
  public static void createProjects() {
    for (int i = 0; i < numberOfProjects; i++) {
      projectList.add(new ProjectFactory().createProject(RANDOM_PROJECT));
    }
    for (Project project : projectList) {
      projectsClient.createProject(project);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список проектов")
  public void getAllProjectTest() {
    getAllProjectResponse = projectsClient.getListOfProjectsForAdmin();
    statusCode = extractStatusCode(getAllProjectResponse);

    assertEquals(SC_OK, statusCode);
  }

  // todo проверить список проектов со списком проектов в ответе
  // + реализовать остальные проверки для query options
}

