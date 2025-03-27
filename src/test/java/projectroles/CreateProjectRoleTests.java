package projectroles;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateProjectRoleTests extends ProjectRolesBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на создание роли в проекте дает ответ 200")
  public void createProjectRoleTest() {
    statusCode = extractStatusCode(createProjectRoleResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(defaultProjectRole.getName(), defaultResponseProjectRole.getName()),
        () -> assertEquals(defaultProjectRole.getIsDefault(),
            defaultResponseProjectRole.getIsDefault()),
        () -> assertEquals(defaultProjectRole.getPermissions(),
            defaultResponseProjectRole.getPermissions())
    );
  }

}

