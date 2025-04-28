package projectroles;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ProjectRoles(Роли в проекте)")
@Story("Создание роли в проекте")
public class CreateProjectRoleTests extends ProjectRolesBaseTests {

  @Test
  @Tag(value = "positive")
  @DisplayName("Запрос на создание роли в проекте дает ответ 200")
  public void createProjectRoleTest() {
    statusCode = extractStatusCode(createProjectRoleResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(defaultCreateProjectRole.getName(), defaultProjectRole.getName()),
        () -> assertEquals(defaultCreateProjectRole.getIsDefault(),
            defaultProjectRole.getIsDefault()),
        () -> assertEquals(defaultCreateProjectRole.getPermissions(),
            defaultProjectRole.getPermissions())
    );
  }

}

