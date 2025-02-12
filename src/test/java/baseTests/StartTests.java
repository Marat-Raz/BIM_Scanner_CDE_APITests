package baseTests;

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
import models.user.User;
import models.user.UserFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class StartTests {

  private static TokenClient tokenClient = new TokenClient();
  protected static User defaultUser;
  protected static String userId;
  protected static ValidatableResponse baseResponse;
  protected static UserClient userClient = new UserClient();
  protected int statusCode;
  protected static UserFactory userFactory = new UserFactory();
  protected ErrorRoot errorRoot;

  @BeforeAll
  @Step("Запуск Allure и логирования запросов по API, \n"
      + "получение токена пользователя admin для запросов, требующих прав админа, \n"
      + "создание пользователя по умолчанию.\n")
  public static void globalSetUp() {
    RestAssured.filters(
        new RequestLoggingFilter(), new ResponseLoggingFilter(),
        new AllureRestAssured());
    ValidatableResponse responseAdminToken =
        tokenClient.createToken(TokenBuilder.getTokenForAdminUser());
    Client.ADMIN_ACCESS_TOKEN = responseAdminToken.extract().path("access_token");
    defaultUser = userFactory.createUser(DEFAULT_USER);
    baseResponse = userClient.createUser(defaultUser);
    userId = baseResponse.extract().path("id");
    ValidatableResponse responseToken =
        tokenClient.createToken(TokenBuilder.getTokenForUser(defaultUser));
    Client.DEFAULT_USER_ACCESS_TOKEN = responseToken.extract().path("access_token");
    // todo выдать для user права на создание проектов раздел permission
  }

  @BeforeEach
  //@Step("Создание пользователя")
  public void setUp() {
// todo добавить необходимую реализацию
  }

  @AfterAll
  @Step("Удаление профиля пользователя")
  public static void cleanData() {
    userClient.deleteUser(userId);
  }

  @Step("Получаем код ответа")
  public int extractStatusCode(ValidatableResponse response) {
    return response.extract().statusCode();
  }

}