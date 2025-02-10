package user;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import io.restassured.response.ValidatableResponse;
import java.util.UUID;
import models.error.ErrorRoot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetUserByIdTests extends StartTests {

  ValidatableResponse getUserResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить пользователя по id")
  public void getUserByIdTest() {
    getUserResponse = userClient.getUserById(userId);
    statusCode = extractStatusCode(getUserResponse);
    String userName = getUserResponse.extract().path("userName");
    String email = getUserResponse.extract().path("email");

    assertEquals(SC_OK, statusCode);
    assertEquals(defaultUser.getUserName(), userName);
    assertEquals(defaultUser.getEmail(), email);
  }

  @Test
  @DisplayName("Получить пользователя по неверному id")
  public void getUserByWrongIdTest() {
    getUserResponse = userClient.getUserById("userId");
    statusCode = extractStatusCode(getUserResponse);
    errorRoot = getUserResponse.extract().body().as(ErrorRoot.class);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", errorRoot.error.message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The value 'userId' is not valid.\n", errorRoot.error.details);
  }

  @Test
  @DisplayName("Получить пользователя по id, отсутствующему в базе")
  public void getUserByMissingIdTest() {
    UUID uuid = UUID.randomUUID();
    String wrongId = String.valueOf(uuid);
    getUserResponse = userClient.getUserById(wrongId);
    statusCode = extractStatusCode(getUserResponse);
    errorRoot = getUserResponse.extract().body().as(ErrorRoot.class);

    assertEquals(SC_NOT_FOUND, statusCode);
    assertEquals("There is no entity IdentityUser with id = "
        + wrongId + "!", errorRoot.error.message);
  }
}
