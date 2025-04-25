package user;

import static dtomodels.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import dto.generated.AbpRemoteServiceErrorResponse;
import dto.generated.AbpIdentityUserCreateDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел User")
@Story("Изменение данных пользователя")
public class ChangeAbpIdentityUserCreateDtoDataTests extends StartTests {

  ValidatableResponse putResponse;
  AbpIdentityUserCreateDto newAbpIdentityUserCreateDto;

  @BeforeEach
  public void createNewUser() {
    newAbpIdentityUserCreateDto = userFactory.createUser(NEW_USER);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить данные пользователя - userName и password")
  public void changeUserDataTests() {
    putResponse = userClient.changeUser(newAbpIdentityUserCreateDto, userId);
    statusCode = putResponse.extract().statusCode();

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - userName, без передачи параметра email")
  public void changeUsernameWithoutEmailTests() {
    AbpIdentityUserCreateDto abpIdentityUserCreateDtoWithoutEmail = newAbpIdentityUserCreateDto;
    abpIdentityUserCreateDtoWithoutEmail.setEmail(null);
    putResponse = userClient.changeUser(abpIdentityUserCreateDtoWithoutEmail, userId);
    statusCode = extractStatusCode(putResponse);
    abpRemoteServiceErrorResponse = putResponse.extract().body().as(AbpRemoteServiceErrorResponse.class);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", abpRemoteServiceErrorResponse.abpRemoteServiceErrorInfo.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The Email field is required.\n", abpRemoteServiceErrorResponse.abpRemoteServiceErrorInfo.details);
  }

  @Test
  @DisplayName("Изменить данные пользователя - userName")
  public void changeUsernameWithEmailTests() {
    putResponse = userClient.changeUser(newAbpIdentityUserCreateDto, userId);
    statusCode = extractStatusCode(putResponse);

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - email")
  public void changeEmailWithUsernameTests() {
    putResponse = userClient.changeUser(defaultAbpIdentityUserCreateDto, userId);
    statusCode = extractStatusCode(putResponse);

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - email, без передачи параметра userName")
  public void changeEmailWithoutUsernameTests() {
    AbpIdentityUserCreateDto userWithoutAbpIdentityUserCreateDtoName = newAbpIdentityUserCreateDto;
    userWithoutAbpIdentityUserCreateDtoName.setUserName(null);
    putResponse = userClient.changeUser(userWithoutAbpIdentityUserCreateDtoName, userId);
    int statusCode = extractStatusCode(putResponse);
    abpRemoteServiceErrorResponse = putResponse.extract().body().as(AbpRemoteServiceErrorResponse.class);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", abpRemoteServiceErrorResponse.abpRemoteServiceErrorInfo.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The UserName field is required.\n", abpRemoteServiceErrorResponse.abpRemoteServiceErrorInfo.details);
  }

}
