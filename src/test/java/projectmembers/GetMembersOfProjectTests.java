package projectmembers;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeProjectMemberDto;
import dtomodels.PaginatedResponse;
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
@Story("Получение списка участников проекта")
public class GetMembersOfProjectTests extends ProjectMembersBaseTest {

  private ValidatableResponse getMembersResponse;
  private PaginatedResponse<CdeProjectMemberDto> paginatedResponse;

  // todo добавить несколько участников в проект

  @Test
  @Tag(value = "positive")
  @DisplayName("Запрос на получение списка участников проекта дает ответ 200")
  public void getMembersOfProjectTest() {
    getMembersResponse = projectMembersClient
        .getListOfProjectMembersWithoutQueryOptions(projectId);

    statusCode = extractStatusCode(getMembersResponse);
    paginatedResponse = getMembersResponse.extract().as(PaginatedResponse.class);
    ArrayList<CdeProjectMemberDto> arrayOfMembers = paginatedResponse.getItems();

    assertEquals(SC_OK, statusCode);
// todo нужно также другие параметры сравнить
  }
}
