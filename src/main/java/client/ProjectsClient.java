package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import models.project.Project;
import models.project.ProjectWithConcurrencyStamp;

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

  @Step("Получить список всех проектов для ADMIN")
  public ValidatableResponse getListOfProjectsForAdmin() {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(PROJECTS)
        .then();
  }

  @Step("Получить список всех проектов по токену определенного пользователя")
  public ValidatableResponse getListOfProjects(String token) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .get(PROJECTS)
        .then();
  }

  @Step("Создать проект с токеном пользователя ADMIN")
  public ValidatableResponse createProject(Project project) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(project)
        .when()
        .post(PROJECTS)
        .then();
  }

  @Step("Создать проект по токену определенного пользователя")
  public ValidatableResponse createProject(String token, Project project) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .body(project)
        .when()
        .post(PROJECTS)
        .then();
  }

  @Step("Получить проект по его id пользователя ADMIN")
  public ValidatableResponse getProjectByItsIdForAdmin(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(PROJECTS + id)
        .then();
  }

  @Step("Получить проект по его id по токену определенного пользователя")
  public ValidatableResponse getProjectByItsId(String token, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .get(PROJECTS + id)
        .then();
  }

  @Step("Удалить проект по его id с токеном DEFAULT_USER")
  public ValidatableResponse deleteProjectByItsId(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(PROJECTS + id)
        .then();
  }

  @Step("Удалить проект по его id с токеном определенного пользователя")
  public ValidatableResponse deleteProjectByItsId(String token, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .delete(PROJECTS + id)
        .then();
  }

  @Step("Изменить проект по его id")
  public ValidatableResponse putProjectByItsId(String id,
      ProjectWithConcurrencyStamp project) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(project)
        .when()
        .put(PROJECTS + id)
        .then();
  }

  @Step("Изменить проект по его id с токеном определенного пользователя")
  public ValidatableResponse putProjectByItsId(String token, String id,
      ProjectWithConcurrencyStamp newProject) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(newProject)
        .when()
        .put(PROJECTS + id)
        .then();
  }

  @Step("Получить изображение обложки проекта с токеном админа")
  public ValidatableResponse getProjectCoverImage(String projectId) {
    return RestAssured.given()
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(PROJECTS + projectId + "/cover") // "api/projects/projectId/cover"
        .then();
  }

  @Step("Получить изображение обложки проекта с токеном определенного пользователя")
  public ValidatableResponse getProjectCoverImage(String token, String projectId) {
    return given()
        .spec(multipartBaseSpec())
        .auth().oauth2(token)
        .when()
        .get(PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Задать изображение обложки проекта с токеном админа")
  public ValidatableResponse setProjectCoverImage(String projectId) {
    return given()
        .spec(multipartBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .multiPart("coverImage", new File("src/main/resources/1.7mb.png"))
        // todo заменить название файла на переменную
        .when()
        .put(PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Задать изображение обложки проекта с токеном определенного пользователя")
  public ValidatableResponse setProjectCoverImage(String token, String projectId) {
    return given()
        .spec(multipartBaseSpec())
        .auth().oauth2(token)
        .multiPart("coverImage", new File("src/main/resources/1.7mb.png"))
        // todo заменить название файла на переменную
        .when()
        .put(PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Удалить изображение обложки проекта с токеном админа")
  public ValidatableResponse deleteProjectCoverImage(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Удалить изображение обложки проекта с токеном определенного пользователя")
  public ValidatableResponse deleteProjectCoverImage(String token, String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .delete(PROJECTS + projectId + "/cover")
        .then();
  }
}
