package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.user.User;
import models.user.UserCredentials;

public class UserClient extends Client {

  private static final String USERS = "/api/identity/users/";

  @Step("Получить пользователя по id")
  public ValidatableResponse getUserById(String accessToken, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .when()
        .post(USERS + id)
        .then();
  }

  @Step("Изменить данные пользователя") // todo уточнить по документации
  public ValidatableResponse changeUser(String accessToken, UserCredentials userCredentials, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .body(userCredentials)
        .when()
        .put(USERS + id)
        .then();
  }

  @Step("Удалить пользователя по id")
  public ValidatableResponse deleteUser(String accessToken, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .when()
        .delete(USERS + id)
        .then();
  }

  @Step("Получить список пользователей по фильтрам")
  public ValidatableResponse getListOfUsers(String accessToken, String filter, String sorting,
      String skipCount, int maxResultCount) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .when()
        .delete(USERS + filter + sorting + skipCount + maxResultCount)
        .then();
  }

  @Step("Создать пользователя")
  public ValidatableResponse createUser(String accessToken, User user) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .body(user)
        .when()
        .post(USERS)
        .then();
  }

  @Step("Получить роли пользователя по его id")
  public ValidatableResponse getUserRolesById(String accessToken, String id) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .when()
        .post(USERS + id + "/roles")
        .then();
  }

  @Step("Изменить роли пользователя по его id")
  public ValidatableResponse getUserRolesById(String accessToken, String id, String roleNames) {
    //todo заменить параметр roleNames после добавления модели ролей
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .body(roleNames)
        .when()
        .post(USERS + id + "/roles")
        .then();
  }

  @Step("Получить список назначаемых пользователям ролей")
  public ValidatableResponse getListOfRolesAssignedToUsers(String accessToken) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .when()
        .post(USERS + "/assignable-roles")
        .then();
  }

  @Step("Получить пользователя по userName")
  public ValidatableResponse getUserByUserName(String accessToken, String userName) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .when()
        .post(USERS + "/by-username/" + userName)
        .then();
  }

  @Step("Получить пользователя по email")
  public ValidatableResponse getUserByEmail(String accessToken, String email) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(accessToken)
        .when()
        .post(USERS + "/by-email/" + email)
        .then();
  }


}
