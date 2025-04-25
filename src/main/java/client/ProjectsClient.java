package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import dto.generated.CdeCreateProjectDto;
import dto.generated.CdeUpdateProjectDto;

public class ProjectsClient extends Client {

  @Step("Получить список всех проектов DEFAULT_USER")
  public ValidatableResponse getListOfProjects() {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(DEFAULT_USER_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS)
        .then();
  }

  @Step("Получить список всех проектов для ADMIN")
  public ValidatableResponse getListOfProjectsForAdmin() {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS)
        .then();
  }

  @Step("Получить список всех проектов по токену определенного пользователя")
  public ValidatableResponse getListOfProjects(String token) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .get(API_PROJECTS)
        .then();
  }

  @Step("Создать проект с токеном пользователя ADMIN")
  public ValidatableResponse createProject(CdeCreateProjectDto cdeCreateProjectDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(cdeCreateProjectDto)
        .when()
        .post(API_PROJECTS)
        .then();
  }

  @Step("Создать проект по токену определенного пользователя")
  public ValidatableResponse createProject(String token, CdeCreateProjectDto cdeCreateProjectDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .body(cdeCreateProjectDto)
        .when()
        .post(API_PROJECTS)
        .then();
  }

  @Step("Получить проект по его id пользователя ADMIN")
  public ValidatableResponse getProjectByItsIdForAdmin(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + id)
        .then();
  }

  @Step("Получить проект по его id по токену определенного пользователя")
  public ValidatableResponse getProjectByItsId(String token, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .get(API_PROJECTS + id)
        .then();
  }

  @Step("Удалить проект по его id с токеном DEFAULT_USER")
  public ValidatableResponse deleteProjectByItsIdWithAdminToken(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + id)
        .then();
  }

  @Step("Удалить проект по его id с токеном определенного пользователя")
  public ValidatableResponse deleteProjectByItsId(String token, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .delete(API_PROJECTS + id)
        .then();
  }

  @Step("Изменить проект по его id")
  public ValidatableResponse putProjectByItsId(String id,
      CdeUpdateProjectDto project) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(project)
        .when()
        .put(API_PROJECTS + id)
        .then();
  }

  @Step("Изменить проект по его id с токеном определенного пользователя")
  public ValidatableResponse putProjectByItsId(String token, String id,
      CdeUpdateProjectDto newProject) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(newProject)
        .when()
        .put(API_PROJECTS + id)
        .then();
  }

  @Step("Получить изображение обложки проекта с токеном админа")
  public ValidatableResponse getProjectCoverImage(String projectId) {
    return RestAssured.given()
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Получить изображение обложки проекта с токеном определенного пользователя")
  public ValidatableResponse getProjectCoverImage(String token, String projectId) {
    return given()
        .spec(multipartBaseSpec())
        .auth().oauth2(token)
        .when()
        .get(API_PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Задать изображение обложки проекта с токеном админа")
  public ValidatableResponse setProjectCoverImage(String projectId, File file) {
    return given()
        .spec(multipartBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .multiPart("coverImage", file)
        // todo заменить название файла на переменную
        .when()
        .put(API_PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Задать изображение обложки проекта с токеном определенного пользователя")
  public ValidatableResponse setProjectCoverImage(String token, String projectId, File file) {
    return given()
        .spec(multipartBaseSpec())
        .auth().oauth2(token)
        .multiPart("coverImage", file)
        // todo заменить название файла на переменную
        .when()
        .put(API_PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Удалить изображение обложки проекта с токеном админа")
  public ValidatableResponse deleteProjectCoverImage(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + projectId + "/cover")
        .then();
  }

  @Step("Удалить изображение обложки проекта с токеном определенного пользователя")
  public ValidatableResponse deleteProjectCoverImage(String token, String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(token)
        .when()
        .delete(API_PROJECTS + projectId + "/cover")
        .then();
  }
}
