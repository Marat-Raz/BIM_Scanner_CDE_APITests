package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.user.User;

public class UserClient extends Client {

  private static final String USERS = "/api/identity/users/";

  @Step("Получить пользователя по id")
  public ValidatableResponse getUserById(String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .post(USERS + id)
        .then();
  }

  @Step("Изменить данные пользователя") // todo уточнить по документации
  public ValidatableResponse changeUser(User user, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(user)
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
      String skipCount, int maxResultCount) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .delete(USERS + filter + sorting + skipCount + maxResultCount)
        .then();
  }

  @Step("Создать пользователя")
  public ValidatableResponse createUser(User user) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(user)
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
        .get(USERS + "/by-username/" + userName)
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
