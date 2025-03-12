package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import models.projectmember.ProjectMember;

public class ProjectMembersClient extends Client {

private final String PROJECT_MEMBERS = "api/projects/";

  @Step("")
  public ValidatableResponse addUserToProjectMembers(String projectId, String userId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body("{\"userId\": \"" + userId + "\"}") // fixme сделать объектно или удалить
        .when()
        .post(PROJECT_MEMBERS + projectId + "/members")
        .then();
  }

  @Step("Получить список участников проекта без QueryOptions")
  public ValidatableResponse getProjectMembersWithoutQueryOptions(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(PROJECT_MEMBERS + projectId + "/members")
        .then();
  }

  @Step("Получить список участников проекта")
  public ValidatableResponse getProjectMembers(String projectId, Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(PROJECT_MEMBERS + projectId + "/members")
        .then();
  }

  @Step("Добавить участника в проект")
  public ValidatableResponse addProjectMember(String projectId, ProjectMember member) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(member)
        .when()
        .post(PROJECT_MEMBERS + projectId + "/members")
        .then();
  }

  @Step("Получить информацию об участнике проекта")
  public ValidatableResponse getProjectMemberById(String projectId, String userId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(PROJECT_MEMBERS + projectId + "/members/" + userId)
        .then();
  }

  @Step("Удалить участника из проекта")
  public ValidatableResponse deleteProjectMember(String projectId, String userId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(PROJECT_MEMBERS + projectId + "/members/" + userId)
        .then();
  }

  @Step("Получить список кандидатов для добавления в проект")
  public ValidatableResponse getProjectMemberCandidates(String projectId, Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .pathParam("projectId", projectId)
        .queryParams(queryParams) 
        .when()
        .get(PROJECT_MEMBERS + projectId + "/member-candidates")
        .then();
  }
}
