package projectroles;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.projectroles.ResponseProjectRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetAllProjectRoles extends ProjectRolesBaseTests {

  @Test
  @Tag(value = "positive")
  @DisplayName("Запрос на получение всех ролей в проекте дает ответ 200")
  public void getAllProjectRolesTest() {
    getAllRolesResponse = projectRolesClient.getProjectRolesWithoutQueryOptions(projectId);
    statusCode = extractStatusCode(getAllRolesResponse);
    ResponseProjectRole[] roleArray = getAllRolesResponse.extract().as(ResponseProjectRole[].class);

    assertEquals(SC_OK, statusCode);
    assertEquals(projectRolesCount, roleArray.length);
  }
}
