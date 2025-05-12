package projectmembers;

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
@Story("Добавление пользователя в проект")
public class AddUserToProjectMembersTests extends ProjectMembersBaseTest {

  @Test
  @Tag(value = "positive")
  @DisplayName("Запрос на добавление пользователя в проект дает ответ 200")
  public void addUserToProjectMembersTest() {
    statusCode = extractStatusCode(addUserToProjectMembersResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(cdeProjectMemberDto.getUser().getId(), userId),
        () -> assertEquals(cdeProjectMemberDto.getRole().getId(), defaultProjectRoleId)
    );
  }
}
