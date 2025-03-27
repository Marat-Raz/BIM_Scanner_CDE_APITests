package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import dtomodels.projectroles.ProjectRole;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class ProjectRolesClient extends Client {

  private final String ROLES = "/roles/";

  @Step("Получить список всех ролей проекта")
  public ValidatableResponse getProjectRoles(String projectId, String roleName) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParam("search", roleName)
        .when()
        .get(API_PROJECTS + projectId + ROLES)
        .then();
  }

  @Step("Получить список всех ролей проекта без параметров поиска")
  public ValidatableResponse getProjectRolesWithoutQueryOptions(String projectId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + ROLES)
        .then();
  }

  @Step("Создать новую роль проекта")
  public ValidatableResponse createProjectRole(String projectId, ProjectRole projectRole) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(projectRole)
        .when()
        .post(API_PROJECTS + projectId + ROLES)
        .then();
  }

  @Step("Получить роль проекта по ID")
  public ValidatableResponse getProjectRoleById(String projectId, String roleId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + ROLES + roleId)
        .then();
  }

  @Step("Обновить роль проекта по ID")
  public ValidatableResponse updateProjectRole(String projectId, String roleId,
      Object updatedRole) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(updatedRole)
        .when()
        .put(API_PROJECTS + projectId + ROLES + roleId)
        .then();
  }

  @Step("Удалить роль проекта по ID")
  public ValidatableResponse deleteProjectRole(String projectId, String roleId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(API_PROJECTS + projectId + ROLES + roleId)
        .then();
  }
}