package client.token;

import static io.restassured.RestAssured.given;
import client.base.Client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.token.RequestToken;

public class TokenClient extends Client {
  private static final String TOKEN = "connect/token";

  @Step("Создание токена для пользователя")
  public ValidatableResponse createToken(RequestToken token) {
    return given()
        .spec(getTokenBaseSpec())
        .body(token.toString())
        .when()
        .post(TOKEN)
        .then();
  }

}
