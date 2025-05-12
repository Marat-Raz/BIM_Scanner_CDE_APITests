package projectmembers;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeProjectMemberDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ProjectRoles(Роли в проекте)")
@Story("Получение информации об участнике проекта по ID")
public class GetProjectMemberByIdTests extends ProjectMembersBaseTest {

  private ValidatableResponse getProjectMemberResponse;
  private CdeProjectMemberDto actualProjectMember;

  @Test
  @Tag(value = "positive")
  @DisplayName("Получение информации об участнике проекта по ID дает ответ 200")
  public void getProjectMemberByIdTest() {
    getProjectMemberResponse = projectMembersClient.getProjectMemberById(projectId, userId);
    statusCode = extractStatusCode(getProjectMemberResponse);
    actualProjectMember = getProjectMemberResponse.extract().as(CdeProjectMemberDto.class);

    assertEquals(SC_OK, statusCode);
  }

}
