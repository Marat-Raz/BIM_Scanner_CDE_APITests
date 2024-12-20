import client.AccountClient;
import client.TokenClient;
import client.UserClient;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import models.token.TokenBuilder;
import models.user.UserGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import models.user.User;
import org.junit.jupiter.api.BeforeEach;


public class StartTests {

  static String accessToken;
  static TokenClient tokenClient = new TokenClient();
  User user;
  AccountClient accountClient;
  String userId;
  ValidatableResponse baseResponse;
  UserClient userClient;


  @BeforeAll
  @Step("Запуск Allure и логирования запросов по API, "
      + "получение токена пользователя admin для запросов, требующих авторизации")
  public static void globalSetUp() {
    RestAssured.filters(
        new RequestLoggingFilter(), new ResponseLoggingFilter(),
        new AllureRestAssured());
    ValidatableResponse responseAdminToken = tokenClient.createToken(TokenBuilder.getTokenForAdminUser());
    accessToken = responseAdminToken.extract().path("access_token");
  }

  @BeforeEach
  @Step("Создание пользователя")
  public void setUp() {
    user = UserGenerator.getUser();
    userClient = new UserClient();
    baseResponse = userClient.createUser(accessToken, user);
    userId = baseResponse.extract().path("id");
  }

  @AfterEach
  @Step("Удаление пользователя")
  public void tearDown() {
    userClient = new UserClient();
    userClient.deleteUser(accessToken, userId);
  }

}