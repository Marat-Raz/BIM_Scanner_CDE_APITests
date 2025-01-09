package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.project.Project;

public class ProjectsClient extends Client {
  private static final String PROJECTS = "api/projects/";

  @Step("Получить список проектов")
  public ValidatableResponse getListOfProjects() {
    return given()
        .spec(getBaseSpec())
        .when()
        .get(PROJECTS)
        .then();
  }

  @Step("Создать проект")
  public ValidatableResponse createProject(Project project) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ACCESS_TOKEN)
        .body(project)
        .when()
        .post(PROJECTS)
        .then();
  }

  @Step("Получить проект по его id")
  public ValidatableResponse getProjectByItsId(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ACCESS_TOKEN)
        .when()
        .get(PROJECTS + id)
        .then();
  }

  @Step("Удалить проект по его id")
  public ValidatableResponse deleteProjectByItsId(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ACCESS_TOKEN)
        .when()
        .delete(PROJECTS + id)
        .then();
  }

}
