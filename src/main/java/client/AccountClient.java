package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.user.User;

public class AccountClient extends Client { // todo необходимо дописать остальные методы/ручки

  private static final String USER = "api/account/";

  @Step("Регистрация пользователя")
  public ValidatableResponse registerUser(User user) {
    return given()
        .spec(getBaseSpec())
        .body(user)
        .when()
        .post(USER + "register/")
        .then();
  }
/*
  @Step("Логин пользователя")
  public ValidatableResponse loginUser(UserCredentials userCredentials) {
    return given()
        .spec(getBaseSpec())
        .body(userCredentials)
        .when()
        .post(USER + "login/")
        .then();
  }*/

 /* @Step("Обновление данных пользователя")
  public ValidatableResponse updateUser(User user, String accessToken) {
    return given()
        .spec(getBaseSpec())
        .header("authorization", accessToken)
        .body(user)
        .when()
        .patch(USER + "models/")
        .then();
  }
*/
  @Step("Удаление пользователя")
  public ValidatableResponse deleteUser(String accessToken, String userId) {
    return given()
        .spec(getBaseSpec())
        .header("authorization", accessToken)
        .when()
        .delete(USER + "models/")
        .then();
  }
}
