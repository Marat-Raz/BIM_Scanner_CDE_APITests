package projectmembers;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
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
@Story("Удаление пользователя из участников проекта")
public class RemoveUserFromProjectMembersTests extends ProjectMembersBaseTest {

  private ValidatableResponse removeMembersResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Удаление пользователя из участников проекта дает ответ 204")
  public void removeUserFromProjectMembersTest() {
    removeMembersResponse = projectMembersClient.deleteProjectMember(projectId, userId);
    statusCode = extractStatusCode(removeMembersResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }
}
