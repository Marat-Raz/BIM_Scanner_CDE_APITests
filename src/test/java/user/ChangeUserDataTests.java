package user;

import static dtomodels.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import dtomodels.error.ErrorRoot;
import dtomodels.user.User;
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
public class ChangeUserDataTests extends StartTests {

  ValidatableResponse putResponse;
  User newUser;

  @BeforeEach
  public void createNewUser() {
    newUser = userFactory.createUser(NEW_USER);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить данные пользователя - userName и password")
  public void changeUserDataTests() {
    putResponse = userClient.changeUser(newUser, userId);
    statusCode = putResponse.extract().statusCode();

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - userName, без передачи параметра email")
  public void changeUsernameWithoutEmailTests() {
    User userWithoutEmail = newUser;
    userWithoutEmail.setEmail(null);
    putResponse = userClient.changeUser(userWithoutEmail, userId);
    statusCode = extractStatusCode(putResponse);
    errorRoot = putResponse.extract().body().as(ErrorRoot.class);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", errorRoot.error.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The Email field is required.\n", errorRoot.error.details);
  }

  @Test
  @DisplayName("Изменить данные пользователя - userName")
  public void changeUsernameWithEmailTests() {
    putResponse = userClient.changeUser(newUser, userId);
    statusCode = extractStatusCode(putResponse);

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - email")
  public void changeEmailWithUsernameTests() {
    putResponse = userClient.changeUser(defaultUser, userId);
    statusCode = extractStatusCode(putResponse);

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - email, без передачи параметра userName")
  public void changeEmailWithoutUsernameTests() {
    User userWithoutUserName = newUser;
    userWithoutUserName.setUserName(null);
    putResponse = userClient.changeUser(userWithoutUserName, userId);
    int statusCode = extractStatusCode(putResponse);
    errorRoot = putResponse.extract().body().as(ErrorRoot.class);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", errorRoot.error.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The UserName field is required.\n", errorRoot.error.details);
  }

}
