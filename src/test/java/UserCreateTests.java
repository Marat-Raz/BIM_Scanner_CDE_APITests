import static models.user.UserType.USER_WITHOUT_EMAIL;
import static models.user.UserType.USER_WITHOUT_NAME;
import static models.user.UserType.USER_WITHOUT_PASSWORD;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.response.ValidatableResponse;
import models.error.ErrorRoot;
import models.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UserCreateTests extends StartTests {

  private ValidatableResponse wrongResponse;

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
    ValidatableResponse secondResponse = userClient.createUser(accessToken, defaultUser);
    statusCode = extractStatusCode(secondResponse);
    errorRoot = secondResponse.extract().body().as(ErrorRoot.class);

    assertEquals(SC_FORBIDDEN, statusCode);
    assertEquals("Username '" + defaultUser.getUserName() +
        "' is already taken., Email '" + defaultUser.getEmail() +
        "' is already taken.", errorRoot.error.message);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - email")
  public void createUserWithoutEmailTest() {
    User userWithoutEmail = userFactory.createUser(USER_WITHOUT_EMAIL);
<<<<<<< Updated upstream
    wrongResponse = userClient.createUser(accessToken, userWithoutEmail);
    message = wrongResponse.extract().path("error.message");
    details = wrongResponse.extract().path("error.details");
=======
    wrongResponse = userClient.createUser(userWithoutEmail);
    errorRoot = wrongResponse.extract().body().as(ErrorRoot.class);
>>>>>>> Stashed changes
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", errorRoot.error.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The Email field is required.\n", errorRoot.error.details);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - password")
  public void createUserWithoutPasswordTest() {
<<<<<<< Updated upstream
    User getUserWithoutPassword = userFactory.createUser(USER_WITHOUT_PASSWORD);
    wrongResponse = userClient.createUser(accessToken, getUserWithoutPassword);
    message = wrongResponse.extract().path("error.message");
    details = wrongResponse.extract().path("error.details");
=======
    User userWithoutPassword = userFactory.createUser(USER_WITHOUT_PASSWORD);
    wrongResponse = userClient.createUser(userWithoutPassword);
    errorRoot = wrongResponse.extract().body().as(ErrorRoot.class);
>>>>>>> Stashed changes
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", errorRoot.error.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The Password field is required.\n", errorRoot.error.details);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - userName")
  public void createUserWithoutNameTest() {
<<<<<<< Updated upstream
    User getUserWithoutUserName = userFactory.createUser(USER_WITHOUT_NAME);
    wrongResponse = userClient.createUser(accessToken, getUserWithoutUserName);
    message = wrongResponse.extract().path("error.message");
    details = wrongResponse.extract().path("error.details");
=======
    User userWithoutUserName = userFactory.createUser(USER_WITHOUT_NAME);
    wrongResponse = userClient.createUser(userWithoutUserName);
    errorRoot = wrongResponse.extract().body().as(ErrorRoot.class);
>>>>>>> Stashed changes
    statusCode = extractStatusCode(wrongResponse);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", errorRoot.error.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The UserName field is required.\n", errorRoot.error.details);
  }
}
