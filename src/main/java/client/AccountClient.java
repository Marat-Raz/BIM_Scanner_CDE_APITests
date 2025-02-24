package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.user.UserCredentials;

public class AccountClient extends Client { // todo необходимо дописать остальные методы

  private static final String USER = "api/account/";

  @Step("Регистрация пользователя")
  public ValidatableResponse registerUser(UserCredentials userCredentials) {
    return given()
        .spec(getBaseSpec())
        .body(userCredentials)
        .when()
        .post(USER + "register/")
        .then();
  }

}
