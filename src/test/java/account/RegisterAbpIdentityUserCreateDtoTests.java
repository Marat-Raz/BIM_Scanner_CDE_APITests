package account;

import static dtomodels.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.AccountClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import dto.generated.AbpRemoteServiceErrorResponse;
import dtomodels.user.User;
import dtomodels.user.UserCredentials;
import org.junit.jupiter.api.*;

@Epic("Api interface CDE")
@Feature("Раздел Account(Аккаунт)")
@Story("Регистрация пользователя")
public class RegisterUserTests extends StartTests {

  private ValidatableResponse registerUserResponse;
  private static User testUser;
  private AccountClient accountClient = new AccountClient();

  /*
// todo регистрация пользователя без необязательных полей
// todo повторная регистрация пользователя с вариантами userName и emailAddress
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

  @Test
  @DisplayName("Повторная регистрация пользователя")
  public void registrationOfPreviouslyRegisteredUserTest() {
    User user = userFactory.createUser(NEW_USER);
    accountClient.registerUser(UserCredentials.from(user));
    registerUserResponse = accountClient.registerUser(UserCredentials.from(user));

    int newStatusCode = extractStatusCode(registerUserResponse);
    abpRemoteServiceErrorResponse = registerUserResponse.extract().body().as(
        AbpRemoteServiceErrorResponse.class);

    ValidatableResponse response = userClient.getUserByUserName(user.getUserName());
    String id = response.extract().path("id");
    userClient.deleteUser(id);

    assertEquals(SC_FORBIDDEN, newStatusCode);
    assertEquals("Username '" + user.getUserName() +
        "' is already taken., Email '" + user.getEmail() +
        "' is already taken.", abpRemoteServiceErrorResponse.abpRemoteServiceErrorInfo.message);
  }

}
