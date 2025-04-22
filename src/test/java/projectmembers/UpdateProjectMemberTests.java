package projectmembers;

import static dtomodels.projectroles.ProjectRolesTypeByRequestStructure.RANDOM_ROLE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.projectmember.CdeUpdateProjectMemberDto;
import dtomodels.projectroles.ProjectRole;
import dtomodels.projectroles.ProjectRolesFactory;
import dtomodels.projectroles.ResponseProjectRole;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ProjectRoles(Роли в проекте)")
@Story("Обновление информации участника проекта (меняется роль в проекте)")
public class UpdateProjectMemberTests extends ProjectMembersBaseTest {

  private ValidatableResponse updateProjectMemberResponse;
  private ProjectRole testProjectRole;
  private String testProjectRoleId;
  private ResponseProjectRole actualProjectRole;

  @BeforeEach
  public void createTestProjectRole() {
    testProjectRole = new ProjectRolesFactory().createRole(RANDOM_ROLE);

    createProjectRoleResponse = projectRolesClient.createProjectRole(projectId,
        defaultProjectRole);
    defaultResponseProjectRole = createProjectRoleResponse.extract()
        .as(ResponseProjectRole.class);
    testProjectRoleId = defaultResponseProjectRole.getId();
  }

  @Test
  @Tag(value = "positive")
  @DisplayName("Обновление информации участника проекта дает ответ 200")
  public void updateProjectMemberTest() {
    CdeUpdateProjectMemberDto updatedMember = new CdeUpdateProjectMemberDto(testProjectRoleId);
    updateProjectMemberResponse =
        projectMembersClient.updateProjectMember(projectId, userId, updatedMember);
    statusCode = extractStatusCode(updateProjectMemberResponse);
    actualProjectRole = updateProjectMemberResponse.extract().as(ResponseProjectRole.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(actualProjectRole.getId(), testProjectRoleId);
  }
}
