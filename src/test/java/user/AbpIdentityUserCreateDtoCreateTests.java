package user;

import static dtomodels.user.UserType.USER_WITHOUT_EMAIL;
import static dtomodels.user.UserType.USER_WITHOUT_PASSWORD;
import static dtomodels.user.UserType.USER_WITHOUT_USERNAME;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import dto.generated.AbpRemoteServiceErrorResponse;
import dto.generated.AbpIdentityUserCreateDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел User")
@Story("Создание пользователя")
public class AbpIdentityUserCreateDtoCreateTests extends StartTests {

  private ValidatableResponse wrongResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создание уникального пользователя возвращает код 200")
  public void userSuccessCreateTest() {
    statusCode = baseResponse.extract().statusCode();
    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Создать пользователя, который уже создан")
  public void createAnExistingUserTest() {
    ValidatableResponse secondResponse = userClient.createUser(defaultAbpIdentityUserCreateDto);
    statusCode = extractStatusCode(secondResponse);
    abpRemoteServiceErrorResponse = secondResponse.extract().body().as(
        AbpRemoteServiceErrorResponse.class);

    assertEquals(SC_FORBIDDEN, statusCode);
    // todo ввести константы для текстов
    assertEquals("Username '" + defaultAbpIdentityUserCreateDto.getUserName() +
        "' is already taken., Email '" + defaultAbpIdentityUserCreateDto.getEmail() +
        "' is already taken.", abpRemoteServiceErrorResponse.getError().getMessage());
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - email")
  public void createUserWithoutEmailTest() {
    AbpIdentityUserCreateDto abpIdentityUserCreateDtoWithoutEmail = userFactory.createUser(USER_WITHOUT_EMAIL);
    wrongResponse = userClient.createUser(abpIdentityUserCreateDtoWithoutEmail);
    abpRemoteServiceErrorResponse = wrongResponse.extract().body().as(AbpRemoteServiceErrorResponse.class);
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", abpRemoteServiceErrorResponse.getError().getMessage());
    assertEquals("The following errors were detected during validation.\n"
        + " - The Email field is required.\n", abpRemoteServiceErrorResponse.getError().getDetails());
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - password")
  public void createUserWithoutPasswordTest() {
    AbpIdentityUserCreateDto abpIdentityUserCreateDtoWithoutPassword = userFactory.createUser(USER_WITHOUT_PASSWORD);
    wrongResponse = userClient.createUser(abpIdentityUserCreateDtoWithoutPassword);
    abpRemoteServiceErrorResponse = wrongResponse.extract().body().as(AbpRemoteServiceErrorResponse.class);
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", abpRemoteServiceErrorResponse.getError().getMessage());
    assertEquals("The following errors were detected during validation.\n"
        + " - The Password field is required.\n", abpRemoteServiceErrorResponse.getError().getDetails());
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - userName")
  public void createUserWithoutNameTest() {
    AbpIdentityUserCreateDto userWithoutAbpIdentityUserCreateDtoName = userFactory.createUser(USER_WITHOUT_USERNAME);
    wrongResponse = userClient.createUser(userWithoutAbpIdentityUserCreateDtoName);
    abpRemoteServiceErrorResponse = wrongResponse.extract().body().as(AbpRemoteServiceErrorResponse.class);
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", abpRemoteServiceErrorResponse.getError().getMessage());
    assertEquals("The following errors were detected during validation.\n"
        + " - The Password field is required.\n"
        + " - The UserName field is required.\n", abpRemoteServiceErrorResponse.getError().getDetails());
  }
}
