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
import dtomodels.error.ErrorRoot;
import dtomodels.user.AbpIdentityUserCreateDto;
import dtomodels.user.UserCredentials;
import org.junit.jupiter.api.*;

@Epic("Api interface CDE")
@Feature("Раздел Account(Аккаунт)")
@Story("Регистрация пользователя")
public class RegisterAbpIdentityUserCreateDtoTests extends StartTests {

  private ValidatableResponse registerUserResponse;
  private static AbpIdentityUserCreateDto testAbpIdentityUserCreateDto;
  private AccountClient accountClient = new AccountClient();

  /*
// todo регистрация пользователя без необязательных полей
// todo повторная регистрация пользователя с вариантами userName и emailAddress
// todo регистрация пользователя с не верными данными: не верные данные в обязательных полях - параметризованные тесты
// todo проверить длину строк параметров полей
 */

  @BeforeAll
  public static void createUser() {
    testAbpIdentityUserCreateDto = userFactory.createUser(NEW_USER);
  }

  @AfterAll
  public static void deleteUser() {
    ValidatableResponse response = userClient.getUserByUserName(testAbpIdentityUserCreateDto.getUserName());
    String id = response.extract().path("id");
    userClient.deleteUser(id);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Регистрация нового пользователя")
  public void newUserRegistrationTest() {
    registerUserResponse = accountClient.registerUser(UserCredentials.from(
        testAbpIdentityUserCreateDto));
    statusCode = extractStatusCode(registerUserResponse);
    String userName = registerUserResponse.extract().path("userName");

    assertEquals(SC_OK, statusCode);
    assertEquals(testAbpIdentityUserCreateDto.getUserName(), userName);
  }

  @Test
  @DisplayName("Повторная регистрация пользователя")
  public void registrationOfPreviouslyRegisteredUserTest() {
    AbpIdentityUserCreateDto abpIdentityUserCreateDto = userFactory.createUser(NEW_USER);
    accountClient.registerUser(UserCredentials.from(abpIdentityUserCreateDto));
    registerUserResponse = accountClient.registerUser(UserCredentials.from(abpIdentityUserCreateDto));

    int newStatusCode = extractStatusCode(registerUserResponse);
    errorRoot = registerUserResponse.extract().body().as(ErrorRoot.class);

    ValidatableResponse response = userClient.getUserByUserName(abpIdentityUserCreateDto.getUserName());
    String id = response.extract().path("id");
    userClient.deleteUser(id);

    assertEquals(SC_FORBIDDEN, newStatusCode);
    assertEquals("Username '" + abpIdentityUserCreateDto.getUserName() +
        "' is already taken., Email '" + abpIdentityUserCreateDto.getEmail() +
        "' is already taken.", errorRoot.error.message);
  }

}
