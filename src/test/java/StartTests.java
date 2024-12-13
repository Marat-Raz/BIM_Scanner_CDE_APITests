import client.account.UserClient;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import models.user.User;


public class StartTests {

  static User user;
  static String accessToken;
  static UserClient userClient;

  @BeforeAll
  @Step("Запуск Allure и логирования запросов по API")
  public static void globalSetUp() {
    RestAssured.filters(
        new RequestLoggingFilter(), new ResponseLoggingFilter(),
        new AllureRestAssured());
  }

  @AfterAll
  @Step("Удаление профиля пользователя")
  public static void tearDown() {/*
    ValidatableResponse loginResponse = userClient.loginUser(UserCredentials.from(user));
    accessToken = loginResponse.extract().path("accessToken");
    if (accessToken != null) {
      userClient.deleteUser(accessToken);
    }*/
  }

}