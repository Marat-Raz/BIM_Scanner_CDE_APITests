package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class BuildInfoClient extends Client {

  private static final String BUILD_INFO = "/api/meta/buildinfo";

  @Step("Получить информацию о версии сборки приложения")
  public ValidatableResponse getBuildInfo() {
    return given()
        .spec(getBaseSpec())
        .when()
        .get(BUILD_INFO)
        .then();
  }

  @Step("") //todo название шага!
  public ValidatableResponse getBuildInfoSemver() {
    return given()
        .spec(getBaseSpec())
        .when()
        .get(BUILD_INFO + "/semver")
        .then();
  }
}
