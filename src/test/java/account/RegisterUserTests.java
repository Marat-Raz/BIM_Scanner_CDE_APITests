package account;

import static models.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.AccountClient;
import io.restassured.response.ValidatableResponse;
import models.user.User;
import models.user.UserCredentials;
import org.junit.jupiter.api.*;
import user.UserCreateTests;

public class RegisterUserTests extends StartTests {

  private ValidatableResponse registerUserResponse;
  private static User testUser;
  private AccountClient accountClient = new AccountClient();
  private UserCredentials userCredentials;

  /*
// todo регистрация пользователя без необязательных полей
// todo регистрация пользователя с не верными данными: не верные данные в обязательных полях - параметризованные тесты
// todo проверить длину строк параметров полей
 */

  @BeforeAll
  public static void createUser() {
    testUser = userFactory.createUser(NEW_USER);
  }

  @AfterAll
  public static void deleteUser() {
    ValidatableResponse response = userClient.getUserByUserName(testUser.getUserName());
    String id = response.extract().path("id");
    userClient.deleteUser(id);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Регистрация нового пользователя")
  public void newUserRegistrationTest() {
    registerUserResponse = accountClient.registerUser(UserCredentials.from(testUser));
    statusCode = extractStatusCode(registerUserResponse);
    String userName = registerUserResponse.extract().path("userName");

    assertEquals(SC_OK, statusCode);
    assertEquals(testUser.getUserName(), userName);
  }

}
