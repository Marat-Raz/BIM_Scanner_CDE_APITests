package projectroles;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ProjectRoles(Роли в проекте)")
@Story("Получение роли в проекте по id")
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
