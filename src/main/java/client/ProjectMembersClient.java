package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import dtomodels.projectmember.CdeUpdateProjectMemberDto;
import dtomodels.projectmember.ProjectMember;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;

public class ProjectMembersClient extends Client {

  private static final String MEMBERS = "/members/";
  private static final String MEMBER_CANDIDATES = "/member-candidates/";

  @Step("Получить список участников проекта с параметрами запроса")
  public ValidatableResponse getListOfProjectMembers(String projectId,
      Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + projectId + MEMBERS)
        .then();
  }

  @Step("Получить список участников проекта без параметров запроса")
  public ValidatableResponse getListOfProjectMembersWithoutQueryOptions(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MEMBERS)
        .then();
  }

  @Step("Добавить участника в проект")
  public ValidatableResponse addMemberToProject(String projectId, ProjectMember member) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(member)
        .when()
        .post(API_PROJECTS + projectId + MEMBERS)
        .then();
  }

  @Step("Получить участника проекта по ID пользователя")
  public ValidatableResponse getProjectMemberById(String projectId,
      String userId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MEMBERS + userId)
        .then();
  }

  @Step("Обновить участника проекта по ID пользователя")
  public ValidatableResponse updateProjectMember(String projectId,
      String userId,
      CdeUpdateProjectMemberDto updatedMember) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedMember)
        .when()
        .put(API_PROJECTS + projectId + MEMBERS + userId)
        .then();
  }

  @Step("Удалить участника из проекта по ID пользователя")
  public ValidatableResponse deleteProjectMember(String projectId,
      String userId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + projectId + MEMBERS + userId)
        .then();
  }

  @Step("Получить список кандидатов в участники проекта с параметрами запроса")
  public ValidatableResponse getListOfMemberCandidates(String projectId,
      Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + projectId + MEMBER_CANDIDATES)
        .then();
  }

  @Step("Получить список кандидатов в участники проекта без параметров запроса")
  public ValidatableResponse getListOfMemberCandidatesWithoutQueryOptions(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MEMBER_CANDIDATES)
        .then();
  }
}