package projectroles;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeProjectRoleDto;
import dto.generated.CdeUpdateProjectRoleDto;
import dto.helpers.DtoConverter;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ProjectRoles(Роли в проекте)")
@Story("Редактирование роли в проекте")
public class UpdateProjectRoleTests extends ProjectRolesBaseTests {

  private ValidatableResponse updateProjectRoleResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Запрос на обновление роли в проекте дает ответ 200")
  public void updateProjectRoleTest() {
    CdeUpdateProjectRoleDto editedRole = DtoConverter.convertDto(defaultProjectRole);
    editedRole.setName("new name");
    updateProjectRoleResponse = projectRolesClient
        .updateProjectRole(projectId, defaultProjectRoleId, editedRole);
    statusCode = extractStatusCode(updateProjectRoleResponse);
    CdeProjectRoleDto actualRole = updateProjectRoleResponse.extract()
        .as(CdeProjectRoleDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(defaultProjectRoleId, actualRole.getId()),
        () -> assertEquals(editedRole.getName(), actualRole.getName())
    );
  }

}
