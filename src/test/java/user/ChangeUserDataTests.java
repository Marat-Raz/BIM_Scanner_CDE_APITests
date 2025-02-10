package user;

import static models.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import io.restassured.response.ValidatableResponse;
import models.error.ErrorRoot;
import models.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ChangeUserDataTests extends StartTests {

  ValidatableResponse putResponse;
  User newUser;

  @BeforeEach
  public void createNewUser() {
    newUser = StartTests.userFactory.createUser(NEW_USER);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить данные пользователя - userName и password")
  public void changeUserDataTests() {
    putResponse = StartTests.userClient.changeUser(newUser, StartTests.userId);
    statusCode = putResponse.extract().statusCode();

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - userName, без передачи параметра email")
  public void changeUsernameWithoutEmailTests() {
    putResponse = StartTests.userClient.changeUser(newUser, StartTests.userId);
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
    putResponse = StartTests.userClient.changeUser(newUser, StartTests.userId);
    statusCode = extractStatusCode(putResponse);

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - email")
  public void changeEmailWithUsernameTests() {
    putResponse = StartTests.userClient.changeUser(StartTests.defaultUser, StartTests.userId);
    statusCode = extractStatusCode(putResponse);

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - email, без передачи параметра userName")
  public void changeEmailWithoutUsernameTests() {
    putResponse = StartTests.userClient.changeUser(StartTests.defaultUser, StartTests.userId);
    int statusCode = extractStatusCode(putResponse);
    errorRoot = putResponse.extract().body().as(ErrorRoot.class);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", errorRoot.error.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The UserName field is required.\n", errorRoot.error.details);
  }

}
