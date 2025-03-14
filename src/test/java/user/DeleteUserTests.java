package user;

import static models.user.UserType.NEW_USER;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.user.User;
import org.junit.jupiter.api.*;

public class DeleteUserTests extends StartTests {

  private String testUserId;
  private ValidatableResponse deleteResponse;
  private User testUser;

  @BeforeEach
  @Step("Создаем пользователя testUser")
  public void createTestUser() {
    testUser = userFactory.createUser(NEW_USER);
    ValidatableResponse response = userClient.createUser(testUser);
    testUserId = response.extract().path("id");
  }

  @AfterEach
  @Step("Удаляем пользователя testUser")
  public void deleteTestUser() {
    if (testUser != null) {
      deleteResponse = userClient.deleteUser(testUserId);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить пользователя testUser")
  public void deleteTestUserTest() {
    deleteResponse = userClient.deleteUser(testUserId);
    statusCode = extractStatusCode(deleteResponse);
    testUser = null; // чтобы в @AfterEach повторно не удалять этого пользователя

    assertEquals(SC_NO_CONTENT, statusCode);
  }

  @Test
  @DisplayName("Удалить пользователя testUser при не верном id")
  public void deleteTestUserWithWrongIdTest() {
    String wrongId = "idTestUser";
    deleteResponse = userClient.deleteUser(wrongId);
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
    deleteResponse = userClient.deleteUserWithoutId();
    statusCode = extractStatusCode(deleteResponse);

    assertEquals(SC_METHOD_NOT_ALLOWED, statusCode);
  }

}
