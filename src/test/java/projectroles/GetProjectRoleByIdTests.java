package projectroles;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetProjectRoleByIdTests extends ProjectRolesBaseTests {

  private ValidatableResponse getProjectRoleResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Запрос на получение роли в проекте по id дает ответ 200")
  public void getProjectRoleByIdTest() {
    getProjectRoleResponse = projectRolesClient.getProjectRoleById(projectId, defaultProjectRoleId);
    statusCode = extractStatusCode(getProjectRoleResponse);
    String id = getProjectRoleResponse.extract().path("id");

    assertEquals(SC_OK, statusCode);
    assertEquals(defaultProjectRoleId, id);
  }

}
