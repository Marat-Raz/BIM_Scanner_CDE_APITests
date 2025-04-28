package user;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import dto.generated.AbpRemoteServiceErrorResponse;
import dto.generated.AbpIdentityUserDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел User")
@Story("Получение пользователя по id")
public class GetUserByIdTests extends StartTests {

  private ValidatableResponse getUserResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить пользователя по id")
  public void getUserByIdTest() {
    getUserResponse = userClient.getUserById(userId);
    statusCode = extractStatusCode(getUserResponse);
    AbpIdentityUserDto abpIdentityUserDto = getUserResponse.extract().as(AbpIdentityUserDto.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(defaultUser.getUserName(), abpIdentityUserDto.getUserName());
    assertEquals(defaultUser.getEmail(), abpIdentityUserDto.getEmail());
  }


  @Test
  @DisplayName("Получить пользователя по неверному id")
  public void getUserByWrongIdTest() {
    getUserResponse = userClient.getUserById("userId");
    statusCode = extractStatusCode(getUserResponse);
    errorResponse = getUserResponse.extract().body().as(
        AbpRemoteServiceErrorResponse.class);

    assertEquals(SC_BAD_REQUEST, statusCode);
    assertEquals("Your request is not valid!", errorResponse.getError().getMessage());
    assertEquals("The following errors were detected during validation.\n"
        + " - The value 'userId' is not valid.\n", errorResponse.getError().getDetails());
  }

  @Test
  @DisplayName("Получить пользователя по id, отсутствующему в базе")
  public void getUserByMissingIdTest() {
    UUID uuid = UUID.randomUUID();
    String wrongId = String.valueOf(uuid);
    getUserResponse = userClient.getUserById(wrongId);
    statusCode = extractStatusCode(getUserResponse);
    errorResponse = getUserResponse.extract().body().as(
        AbpRemoteServiceErrorResponse.class);

    assertEquals(SC_NOT_FOUND, statusCode);
    assertEquals("There is no entity IdentityUser with id = "
        + wrongId + "!", errorResponse.getError().getMessage());
  }

}
