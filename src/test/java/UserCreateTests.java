import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.response.ValidatableResponse;
import models.user.User;
import models.user.UserGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UserCreateTests extends StartTests {

  String message, errorCode, details;
  ValidatableResponse wrongResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создание уникального пользователя возвращает код 200")
  public void userSuccessCreateTest() {
    statusCode = baseResponse.extract().statusCode();
    assertEquals(SC_OK, statusCode);
    assertNotNull(accessToken);
  }

  @Test
  @DisplayName("Создать пользователя, который уже создан")
  public void createAnExistingUserTest() {
    ValidatableResponse secondResponse = userClient.createUser(accessToken, user);
    statusCode = extractStatusCode(secondResponse);
    message = secondResponse.extract().path("error.message");
    errorCode = secondResponse.extract().path("error.code");

    assertEquals(SC_FORBIDDEN, statusCode);
    assertEquals("Username '" + user.getUserName() +
        "' is already taken., Email '" + user.getEmail() +
        "' is already taken.", message);
    assertEquals("Volo.Abp.Identity:DuplicateUserName", errorCode);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - email")
  public void createUserWithoutEmailTest() {
    User getUserWithoutEmail = UserGenerator.getUserWithoutEmail();
    wrongResponse = userClient.createUser(accessToken, getUserWithoutEmail);
    message = wrongResponse.extract().path("error.message");
    details = wrongResponse.extract().path("error.details");
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The Email field is required.\n", details);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - password")
  public void createUserWithoutPasswordTest() {
    User getUserWithoutPassword = UserGenerator.getUserWithoutPassword();
    wrongResponse = userClient.createUser(accessToken, getUserWithoutPassword);
    message = wrongResponse.extract().path("error.message");
    details = wrongResponse.extract().path("error.details");
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The Password field is required.\n", details);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - userName")
  public void createUserWithoutNameTest() {
    User getUserWithoutUserName = UserGenerator.getUserWithoutUserName();
    wrongResponse = userClient.createUser(accessToken, getUserWithoutUserName);
    message = wrongResponse.extract().path("error.message");
    details = wrongResponse.extract().path("error.details");
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The UserName field is required.\n", details);
  }
}
