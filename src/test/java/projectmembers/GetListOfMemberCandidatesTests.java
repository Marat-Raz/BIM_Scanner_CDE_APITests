package projectmembers;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtomodels.PaginatedResponse;
import dtomodels.user.CdeUserDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ProjectRoles(Роли в проекте)")
@Story("Получение списка кандидатов в участники проекта")
public class GetListOfMemberCandidatesTests extends ProjectMembersBaseTest {

  private ValidatableResponse getMemberCandidatesResponse;
  private PaginatedResponse<CdeUserDto> paginatedResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Получение списка кандидатов в участники проекта дает ответ 200")
  public void getListOfMemberCandidatesTest() {
    getMemberCandidatesResponse = projectMembersClient
        .getListOfProjectMembersWithoutQueryOptions(projectId);

    statusCode = extractStatusCode(getMemberCandidatesResponse);
    paginatedResponse = getMemberCandidatesResponse.extract().as(PaginatedResponse.class);
    ArrayList<CdeUserDto> arrayOfMemberCandidates = paginatedResponse.getItems();

    assertEquals(SC_OK, statusCode);
    assertTrue(paginatedResponse.getTotalCount() > 0);
  }
}
