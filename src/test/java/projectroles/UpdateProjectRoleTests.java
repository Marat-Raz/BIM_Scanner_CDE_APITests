package projectroles;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.projectroles.ResponseProjectRole;
import dtomodels.projectroles.projectrolestoedit.ProjectRoleToEdit;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdateProjectRoleTests extends ProjectRolesBaseTests {

  private ValidatableResponse updateProjectRoleResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Запрос на обновление роли в проекте дает ответ 200")
  public void updateProjectRoleTest() {
    ProjectRoleToEdit editedRole = ProjectRoleToEdit.from(defaultResponseProjectRole);
    editedRole.setName("new name");
    updateProjectRoleResponse = projectRolesClient
        .updateProjectRole(projectId, defaultProjectRoleId, editedRole);
    statusCode = extractStatusCode(updateProjectRoleResponse);
    ResponseProjectRole actualRole = updateProjectRoleResponse.extract()
        .as(ResponseProjectRole.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(defaultProjectRoleId, actualRole.getId()),
        () -> assertEquals(editedRole.getName(), actualRole.getName())
    );
  }

}
