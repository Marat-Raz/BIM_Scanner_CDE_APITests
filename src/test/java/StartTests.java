import static models.user.UserType.DEFAULT_USER;

import client.TokenClient;
import client.UserClient;
import client.base.Client;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import models.error.ErrorRoot;
import models.token.TokenBuilder;
import models.user.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import models.user.User;
import org.junit.jupiter.api.BeforeEach;


public class StartTests {

  private static TokenClient tokenClient = new TokenClient();
  protected User defaultUser;
  private String userId;
  protected ValidatableResponse baseResponse;
  protected UserClient userClient = new UserClient();
  protected int statusCode;
  protected UserFactory userFactory = new UserFactory();
  protected ErrorRoot errorRoot;
  protected String message;

  @BeforeAll
  @Step("Запуск Allure и логирования запросов по API, "
      + "получение токена пользователя admin для запросов, требующих авторизации")
  public static void globalSetUp() {
    RestAssured.filters(
        new RequestLoggingFilter(), new ResponseLoggingFilter(),
        new AllureRestAssured());
    ValidatableResponse responseAdminToken = tokenClient.createToken(TokenBuilder.getTokenForAdminUser());
    Client.ACCESS_TOKEN = responseAdminToken.extract().path("access_token");;
  }

  @BeforeEach
  @Step("Создание пользователя")
  public void setUp() {
    defaultUser = userFactory.createUser(DEFAULT_USER);
    baseResponse = userClient.createUser(defaultUser);
    userId = baseResponse.extract().path("id");
  }

  @AfterEach
  @Step("Удаление профиля пользователя")
  public void cleanData() {
    userClient.deleteUser(userId);
  }

  @Step("Получаем код ответа")
  public int extractStatusCode(ValidatableResponse response) {
    return response.extract().statusCode();
  }

}