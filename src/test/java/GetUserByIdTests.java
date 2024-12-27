import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetUserByIdTests extends StartTests {

  ValidatableResponse getUserResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить пользователя по id")
  public void getUserByIdTest() {
    getUserResponse = userClient.getUserById(accessToken, userId);
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
    getUserResponse = userClient.getUserById(accessToken, "userId");
    statusCode = extractStatusCode(getUserResponse);
    message = getUserResponse.extract().path("error.message");
    details = getUserResponse.extract().path("error.details");

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The value 'userId' is not valid.\n", details);
  }

  @Test
  @DisplayName("Получить пользователя по id с не верной авторизацией")
  public void getUserByIdWithWrongTokenTest() {
    getUserResponse = userClient.getUserById("accessToken", userId);
    statusCode = extractStatusCode(getUserResponse);

    assertEquals(SC_UNAUTHORIZED, statusCode);
  }

  @Test
  @DisplayName("Получить пользователя по id, отсутствующему в базе")
  public void getUserByMissingIdTest() {
    UUID uuid = UUID.randomUUID();
    String wrongId = String.valueOf(uuid);
    getUserResponse = userClient.getUserById(accessToken, wrongId);
    statusCode = extractStatusCode(getUserResponse);
    message = getUserResponse.extract().path("error.message");

    assertEquals(SC_NOT_FOUND, statusCode);
    assertEquals("There is no entity IdentityUser with id = " + wrongId + "!", message);
  }
}
