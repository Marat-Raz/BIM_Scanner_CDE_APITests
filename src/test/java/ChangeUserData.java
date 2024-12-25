import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.user.User;
import models.user.UserCredentials;
import models.user.UserGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ChangeUserData extends StartTests {

  ValidatableResponse putResponse;
/*  todo в BeforeEach создать пользователя testUser, данными которого будем менять данные пользователя user
 * удалить пользователя testUser в AfterEach
 * передать только userName
 * передать только email


  */

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить данные пользователя")
  public void changeUserDataTests() {
    User newUser = UserGenerator.getNewUser();
    putResponse = userClient.changeUser(accessToken, UserCredentials.from(newUser), userId);
    int statusCode = putResponse.extract().statusCode();

    assertEquals(SC_OK, statusCode);

  }

}
