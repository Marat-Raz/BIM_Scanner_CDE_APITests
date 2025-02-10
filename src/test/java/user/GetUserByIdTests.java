package user;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

import baseTests.StartTests;
import io.restassured.response.ValidatableResponse;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetUserByIdTests extends StartTests {

  ValidatableResponse getUserResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить пользователя по id")
  public void getUserByIdTest() {
    getUserResponse = StartTests.userClient.getUserById(StartTests.userId);
    statusCode = extractStatusCode(getUserResponse);
    String userName = getUserResponse.extract().path("userName");
    String email = getUserResponse.extract().path("email");

    Assertions.assertEquals(SC_OK, statusCode);
    Assertions.assertEquals(StartTests.defaultUser.getUserName(), userName);
    Assertions.assertEquals(StartTests.defaultUser.getEmail(), email);
  }

  @Test
  @DisplayName("Получить пользователя по неверному id")
  public void getUserByWrongIdTest() {
    getUserResponse = StartTests.userClient.getUserById("userId");
    statusCode = extractStatusCode(getUserResponse);
    message = getUserResponse.extract().path("error.message");
    details = getUserResponse.extract().path("error.details");

    Assertions.assertEquals(SC_BAD_REQUEST, statusCode);
    Assertions.assertEquals("Your request is not valid!", message);
    Assertions.assertEquals("The following errors were detected during validation.\n"
        + " - The value 'userId' is not valid.\n", details);
  }

  @Test
  @DisplayName("Получить пользователя по id, отсутствующему в базе")
  public void getUserByMissingIdTest() {
    UUID uuid = UUID.randomUUID();
    String wrongId = String.valueOf(uuid);
    getUserResponse = StartTests.userClient.getUserById(wrongId);
    statusCode = extractStatusCode(getUserResponse);
    message = getUserResponse.extract().path("error.message");

    Assertions.assertEquals(SC_NOT_FOUND, statusCode);
    Assertions.assertEquals("There is no entity IdentityUser with id = " + wrongId + "!", message);
  }
}
