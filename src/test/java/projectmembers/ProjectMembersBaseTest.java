package projectmembers;

import static dtomodels.projectroles.ProjectRolesTypeByRequestStructure.RANDOM_ROLE;

import basetests.StartTests;
import client.ProjectMembersClient;
import client.ProjectRolesClient;
import dtomodels.projectmember.ProjectMember;
import dtomodels.projectmember.CdeProjectMemberDto;
import dtomodels.projectroles.ProjectRole;
import dtomodels.projectroles.ProjectRolesFactory;
import dtomodels.projectroles.ResponseProjectRole;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ProjectMembersBaseTest extends StartTests {

  protected static ProjectRolesClient projectRolesClient = new ProjectRolesClient();
  protected static ProjectMembersClient projectMembersClient = new ProjectMembersClient();
  protected static ValidatableResponse createProjectRoleResponse;
  protected static ValidatableResponse getAllRolesResponse;
  protected static ValidatableResponse addUserToProjectMembersResponse;
  protected static ProjectRole defaultProjectRole;
  protected static ProjectMember projectMember;
  protected static CdeProjectMemberDto cdeProjectMemberDto;
  protected static ResponseProjectRole defaultResponseProjectRole;
  protected static String defaultProjectRoleId;

  @BeforeAll
  public static void createDefaultProjectRoleAndAddUserToProjectMembers() {
    defaultProjectRole = new ProjectRolesFactory().createRole(RANDOM_ROLE);

    createProjectRoleResponse = projectRolesClient.createProjectRole(projectId,
        defaultProjectRole);
    defaultResponseProjectRole = createProjectRoleResponse.extract()
        .as(ResponseProjectRole.class);
    defaultProjectRoleId = defaultResponseProjectRole.getId();

    projectMember = new ProjectMember(userId, defaultProjectRoleId);

    addUserToProjectMembersResponse = projectMembersClient
        .addMemberToProject(projectId, projectMember);
    cdeProjectMemberDto = addUserToProjectMembersResponse.extract()
        .as(CdeProjectMemberDto.class);
  }

  @AfterAll
  public static void cleanupAllRoles() {
    getAllRolesResponse = projectRolesClient.getProjectRolesWithoutQueryOptions(projectId);
    ResponseProjectRole[] roleArray = getAllRolesResponse.extract().as(ResponseProjectRole[].class);

    for (ResponseProjectRole role : roleArray) {
      projectRolesClient.deleteProjectRole(projectId, role.getId());
    }
  }

}
