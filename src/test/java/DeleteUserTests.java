import static models.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.user.User;
import org.junit.jupiter.api.*;

public class DeleteUserTests extends StartTests {

  String idTestUser;
  ValidatableResponse deleteResponse;
  User testUser;

  @BeforeEach
  @Step("Создаем пользователя testUser")
  public void createTestUser() {
    testUser = userFactory.createUser(NEW_USER);
    ValidatableResponse response = userClient.createUser(accessToken, testUser);
    idTestUser = response.extract().path("id");
  }

  @AfterEach
  @Step("Удаляем пользователя testUser")
  public void deleteTestUser() {
    if (testUser != null) {
      deleteResponse = userClient.deleteUser(accessToken, idTestUser);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить пользователя testUser")
  public void deleteTestUserTest() {
    deleteResponse = userClient.deleteUser(accessToken, idTestUser);
    statusCode = extractStatusCode(deleteResponse);
    testUser = null; // чтобы в @AfterEach повторно не удалять этого пользователя

    assertEquals(SC_NO_CONTENT, statusCode);
  }

  @Test
  @DisplayName("Удалить пользователя testUser с не верным accessToken")
  public void deleteTestUserWithoutTokenTest() {
    deleteResponse = userClient.deleteUser("accessToken", idTestUser);
    statusCode = extractStatusCode(deleteResponse);

    assertEquals(SC_UNAUTHORIZED, statusCode);
  }

  @Test
  @DisplayName("Удалить пользователя testUser при не верном id")
  public void deleteTestUserWithWrongIdTest() {
    String wrongId = "idTestUser";
    deleteResponse = userClient.deleteUser(accessToken, wrongId);
    statusCode = extractStatusCode(deleteResponse);
    String message = deleteResponse.extract().path("error.message");
    String details = deleteResponse.extract().path("error.details");

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", message);
    assertEquals("The following errors were detected during validation.\n"
        + " - The value '" + wrongId + "' is not valid.\n", details);
  }

  @Test
  @DisplayName("Удалить пользователя testUser при отсутствующем id")
  public void deleteTestUserWithoutIdTest() {
    deleteResponse = userClient.deleteUserWithoutId(accessToken);
    statusCode = extractStatusCode(deleteResponse);

    assertEquals(SC_METHOD_NOT_ALLOWED, statusCode);
  }

}
