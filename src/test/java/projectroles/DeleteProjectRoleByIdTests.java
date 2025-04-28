package projectroles;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeProjectRoleDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ProjectRoles(Роли в проекте)")
@Story("Удаление роли из проекта")
public class DeleteProjectRoleByIdTests extends ProjectRolesBaseTests {

  private ValidatableResponse deleteProjectRoleResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Запрос на удаление роли в проекте по id дает ответ 204")
  public void deleteProjectRoleByIdTest() {
    getAllRolesResponse = projectRolesClient.getProjectRolesWithoutQueryOptions(projectId);
    CdeProjectRoleDto[] roleArray = getAllRolesResponse.extract().as(CdeProjectRoleDto[].class);
    deleteProjectRoleResponse = projectRolesClient.deleteProjectRole(projectId,
        roleArray[0].getId());
    statusCode = extractStatusCode(deleteProjectRoleResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
