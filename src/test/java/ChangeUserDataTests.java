import static models.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.user.User;
import models.user.UserCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
    putResponse = userClient.changeUser(accessToken, UserCredentials.from(newUser), userId);
    int statusCode = putResponse.extract().statusCode();

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - userName, без передачи параметра email")
  public void changeUsernameWithoutEmailTests() {

    putResponse = userClient.changeUser(accessToken,
        new UserCredentials(newUser.getUserName(), null), userId);
    int statusCode = extractStatusCode(putResponse);
    message = putResponse.extract().path("error.message");
    details = putResponse.extract().path("error.details");

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The Email field is required.\n", details);
  }

  @Test
  @DisplayName("Изменить данные пользователя - userName")
  public void changeUsernameWithEmailTests() {
    putResponse = userClient.changeUser(accessToken,
        new UserCredentials(newUser.getUserName(), defaultUser.getEmail()), userId);
    int statusCode = extractStatusCode(putResponse);

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - email")
  public void changeEmailWithUsernameTests() {
    putResponse = userClient.changeUser(accessToken,
        new UserCredentials(defaultUser.getUserName(), newUser.getEmail()), userId);
    int statusCode = extractStatusCode(putResponse);

    assertEquals(SC_OK, statusCode);
  }

  @Test
  @DisplayName("Изменить данные пользователя - email, без передачи параметра userName")
  public void changeEmailWithoutUsernameTests() {
    putResponse = userClient.changeUser(accessToken,
        new UserCredentials(null, newUser.getEmail()), userId);
    int statusCode = extractStatusCode(putResponse);
    message = putResponse.extract().path("error.message");
    details = putResponse.extract().path("error.details");

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The UserName field is required.\n", details);  }

}
