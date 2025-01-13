import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.TokenClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest extends StartTests {

  int statusCode;
  boolean isSuccess;
  String userId;
  TokenClient tokenClient = new TokenClient();
// todo Создать пользователя при не верном accessToken

  @Test
  @DisplayName("Создать уникального пользователя")
  public void userSuccessCreateTest() {
    statusCode = baseResponse.extract().statusCode();
    //isSuccess = baseResponse.extract().path("success");
    //accessToken = response.extract().path("accessToken");
    //String refreshToken = baseResponse.extract().path("refreshToken");

    assertEquals(SC_OK, statusCode);
    //assertTrue(isSuccess);
    //assertNotNull(refreshToken);
  }

 /* @Test
  @DisplayName("Создать пользователя, который уже зарегистрирован")
  public void createAnExistingUserTest() {
    ValidatableResponse secondResponse = userClient.createUser(user);
    String message = secondResponse.extract().path("message");
    statusCode = secondResponse.extract().statusCode();
    isSuccess = secondResponse.extract().path("success");

    assertFalse(isSuccess);
    assertEquals(SC_FORBIDDEN, statusCode);
    assertEquals("User already exists", message);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - email")
  public void createUserWithoutLoginTest() {
    User getUserWithoutLogin = UserGenerator.getUserWithoutEmail();
    ValidatableResponse wrongResponse = userClient.createUser(getUserWithoutLogin);
    String message = wrongResponse.extract().path("message");
    statusCode = wrongResponse.extract().statusCode();
    isSuccess = wrongResponse.extract().path("success");

    assertFalse(isSuccess);
    assertEquals(SC_FORBIDDEN, statusCode);
    assertEquals("Email, password and name are required fields", message);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - password")
  public void createUserWithoutPasswordTest() {
    User getUserWithoutPassword = UserGenerator.getUserWithoutPassword();
    ValidatableResponse wrongResponse = userClient.createUser(getUserWithoutPassword);
    String message = wrongResponse.extract().path("message");
    statusCode = wrongResponse.extract().statusCode();
    isSuccess = wrongResponse.extract().path("success");

    assertFalse(isSuccess);
    assertEquals(SC_FORBIDDEN, statusCode);
    assertEquals("Email, password and name are required fields", message);
  }

  @Test
  @DisplayName("Создать пользователя и не заполнить одно из обязательных полей - name")
  public void createUserWithoutNameTest() {
    User getUserWithoutNameName = UserGenerator.getUserWithoutName();
    ValidatableResponse wrongResponse = userClient.createUser(getUserWithoutNameName);
    String message = wrongResponse.extract().path("message");
    statusCode = wrongResponse.extract().statusCode();
    isSuccess = wrongResponse.extract().path("success");

    assertFalse(isSuccess);
    assertEquals(SC_FORBIDDEN, statusCode);
    assertEquals("Email, password and name are required fields", message);
  }
*/
}
