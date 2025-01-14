package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.project.Project;

public class ProjectsClient extends Client {
  private static final String PROJECTS = "api/projects/";

  @Step("Получить список всех проектов DEFAULT_USER")
  public ValidatableResponse getListOfProjects() {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(DEFAULT_USER_ACCESS_TOKEN)
        .when()
        .get(PROJECTS)
        .then();
  }

  @Step("Получить список всех проектов DEFAULT_USER")
  public ValidatableResponse getListOfProjects(String token) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .get(PROJECTS)
        .then();
  }

  @Step("Создать проект с токеном пользователя по умолчанию")
  public ValidatableResponse createProject(Project project) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(DEFAULT_USER_ACCESS_TOKEN)
        .body(project)
        .when()
        .post(PROJECTS)
        .then();
  }

  @Step("Создать проект с любым токеном")
  public ValidatableResponse createProject(String token, Project project) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .body(project)
        .when()
        .post(PROJECTS)
        .then();
  }

  @Step("Получить проект по его id")
  public ValidatableResponse getProjectByItsId(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(DEFAULT_USER_ACCESS_TOKEN)
        .when()
        .get(PROJECTS + id)
        .then();
  }

  @Step("Удалить проект по его id")
  public ValidatableResponse deleteProjectByItsId(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(DEFAULT_USER_ACCESS_TOKEN)
        .when()
        .delete(PROJECTS + id)
        .then();
  }

}
