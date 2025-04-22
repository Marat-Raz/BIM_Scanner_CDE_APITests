package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import dtomodels.user.AbpIdentityUserCreateDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserClient extends Client {

  private static final String USERS = "/api/identity/users/";

  @Step("Получить пользователя по id")
  public ValidatableResponse getUserById(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(USERS + id)
        .then();
  }

  @Step("Изменить данные пользователя") // todo уточнить по документации
  public ValidatableResponse changeUser(AbpIdentityUserCreateDto abpIdentityUserCreateDto, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(abpIdentityUserCreateDto)
        .when()
        .put(USERS + id)
        .then();
  }

  @Step("Удалить пользователя по id")
  public ValidatableResponse deleteUser(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(USERS + id)
        .then();
  }

  @Step("Удалить пользователя по id")
  public ValidatableResponse deleteUserWithoutId() {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(USERS)
        .then();
  }

  @Step("Получить список пользователей по фильтрам")
  public ValidatableResponse getListOfUsers(String filter, String sorting,
      // todo внедрить HashMap тут
      String skipCount, int maxResultCount) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(USERS + filter + sorting + skipCount + maxResultCount)
        .then();
  }

  @Step("Создать пользователя")
  public ValidatableResponse createUser(AbpIdentityUserCreateDto abpIdentityUserCreateDto) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(abpIdentityUserCreateDto)
        .when()
        .post(USERS)
        .then();
  }

  @Step("Получить роли пользователя по его id")
  public ValidatableResponse getUserRolesById(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(USERS + id + "/roles")
        .then();
  }

  @Step("Изменить роли пользователя по его id")
  public ValidatableResponse putUserRolesById(String id, String roleNames) {
    //todo заменить параметр roleNames после добавления модели ролей
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(roleNames)
        .when()
        .put(USERS + id + "/roles")
        .then();
  }

  @Step("Получить список назначаемых пользователям ролей")
  public ValidatableResponse getListOfRolesAssignedToUsers() {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(USERS + "/assignable-roles")
        .then();
  }

  @Step("Получить пользователя по userName")
  public ValidatableResponse getUserByUserName(String userName) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(USERS + "by-username/" + userName)
        .then();
  }

  @Step("Получить пользователя по email")
  public ValidatableResponse getUserByEmail(String email) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(USERS + "/by-email/" + email)
        .then();
  }


}
