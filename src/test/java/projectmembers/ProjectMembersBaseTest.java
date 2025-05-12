package projectmembers;

import static dtomodels.projectroles.ProjectRolesTypeByRequestStructure.RANDOM_ROLE;

import basetests.StartTests;
import client.ProjectMembersClient;
import client.ProjectRolesClient;
import dto.generated.CdeAddProjectMemberDto;
import dto.generated.CdeCreateProjectRoleDto;
import dto.generated.CdeProjectMemberDto;
import dto.generated.CdeProjectRoleDto;
import dtomodels.projectroles.ProjectRolesFactory;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ProjectMembersBaseTest extends StartTests {

  protected static ProjectRolesClient projectRolesClient = new ProjectRolesClient();
  protected static ProjectMembersClient projectMembersClient = new ProjectMembersClient();
  protected static ValidatableResponse createProjectRoleResponse;
  protected static ValidatableResponse getAllRolesResponse;
  protected static ValidatableResponse addUserToProjectMembersResponse;
  protected static CdeCreateProjectRoleDto defaultProjectRole;
  protected static CdeAddProjectMemberDto projectMember;
  protected static CdeProjectMemberDto cdeProjectMemberDto;
  protected static CdeProjectRoleDto defaultResponseProjectRole;
  protected static String defaultProjectRoleId;

  @BeforeAll
  public static void createDefaultProjectRoleAndAddUserToProjectMembers() {
    defaultProjectRole = new ProjectRolesFactory().createRole(RANDOM_ROLE);

    createProjectRoleResponse = projectRolesClient.createProjectRole(projectId,
        defaultProjectRole);
    defaultResponseProjectRole = createProjectRoleResponse.extract()
        .as(CdeProjectRoleDto.class);
    defaultProjectRoleId = defaultResponseProjectRole.getId();

    projectMember = new CdeAddProjectMemberDto(userId, defaultProjectRoleId);

    addUserToProjectMembersResponse = projectMembersClient
        .addMemberToProject(projectId, projectMember);
    cdeProjectMemberDto = addUserToProjectMembersResponse.extract()
        .as(CdeProjectMemberDto.class);
  }

  @AfterAll
  public static void cleanupAllRoles() {
    getAllRolesResponse = projectRolesClient.getProjectRolesWithoutQueryOptions(projectId);
    CdeProjectRoleDto[] roleArray = getAllRolesResponse.extract().as(CdeProjectRoleDto[].class);

    for (CdeProjectRoleDto role : roleArray) {
      projectRolesClient.deleteProjectRole(projectId, role.getId());
    }
  }

}
