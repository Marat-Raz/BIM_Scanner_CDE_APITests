package projectroles;

import static dtomodels.projectroles.ProjectRolesTypeByRequestStructure.RANDOM_ROLE;

import basetests.StartTests;
import client.ProjectRolesClient;
import dto.generated.CdeCreateProjectRoleDto;
import dtomodels.projectroles.ProjectRolesFactory;
import dto.generated.CdeProjectRoleDto;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ProjectRolesBaseTests extends StartTests {

  protected static ProjectRolesClient projectRolesClient = new ProjectRolesClient();
  protected static ValidatableResponse createProjectRoleResponse;
  protected static ValidatableResponse getAllRolesResponse;
  protected static CdeCreateProjectRoleDto defaultCdeCreateProjectRoleDto;
  protected static CdeProjectRoleDto defaultCdeProjectRoleDto;
  protected static String defaultProjectRoleId;
  protected static int projectRolesCount = 3;

  @BeforeAll
  public static void createDefaultProjectRole() {
    for (int i = 0; i < projectRolesCount; i++) {
      defaultCdeCreateProjectRoleDto = new ProjectRolesFactory().createRole(RANDOM_ROLE);
      createProjectRoleResponse = projectRolesClient.createProjectRole(projectId,
          defaultCdeCreateProjectRoleDto);
      defaultCdeProjectRoleDto = createProjectRoleResponse.extract()
          .as(CdeProjectRoleDto.class);
      defaultProjectRoleId = defaultCdeProjectRoleDto.getId();
    }
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
