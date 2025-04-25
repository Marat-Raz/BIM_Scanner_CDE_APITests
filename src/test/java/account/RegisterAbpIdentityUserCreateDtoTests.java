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
import dto.generated.AbpIdentityUserCreateDto;
import dto.generated.AbpRegisterDto;
import org.junit.jupiter.api.*;

@Epic("Api interface CDE")
@Feature("Раздел Account(Аккаунт)")
@Story("Регистрация пользователя")
public class RegisterAbpIdentityUserCreateDtoTests extends StartTests {

  private ValidatableResponse registerUserResponse;
  private static AbpIdentityUserCreateDto testUser;
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
    registerUserResponse = accountClient.registerUser(AbpRegisterDto.from(
        testUser));
    statusCode = extractStatusCode(registerUserResponse);
    String userName = registerUserResponse.extract().path("userName");

    assertEquals(SC_OK, statusCode);
    assertEquals(testUser.getUserName(), userName);
  }

  @Test
  @DisplayName("Повторная регистрация пользователя")
  public void registrationOfPreviouslyRegisteredUserTest() {
    AbpIdentityUserCreateDto duplicatedUser = userFactory.createUser(NEW_USER);
    accountClient.registerUser(AbpRegisterDto.from(duplicatedUser));
    registerUserResponse = accountClient.registerUser(AbpRegisterDto.from(duplicatedUser));

    int newStatusCode = extractStatusCode(registerUserResponse);
    abpRemoteServiceErrorResponse = registerUserResponse.extract().body().as(
        AbpRemoteServiceErrorResponse.class);

    ValidatableResponse response = userClient.getUserByUserName(duplicatedUser.getUserName());
    String id = response.extract().path("id");
    userClient.deleteUser(id);

    assertEquals(SC_FORBIDDEN, newStatusCode);
    assertEquals("Username '" + duplicatedUser.getUserName() +
        "' is already taken., Email '" + duplicatedUser.getEmail() +
        "' is already taken.", abpRemoteServiceErrorResponse.abpRemoteServiceErrorInfo.message);
  }

}
