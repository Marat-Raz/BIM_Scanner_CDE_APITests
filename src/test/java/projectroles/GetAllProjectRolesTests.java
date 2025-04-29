package projectroles;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.projectroles.ResponseProjectRole;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ProjectRoles(Роли в проекте)")
@Story("Получение всех ролей в проекте")
public class GetAllProjectRolesTests extends ProjectRolesBaseTests {

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
