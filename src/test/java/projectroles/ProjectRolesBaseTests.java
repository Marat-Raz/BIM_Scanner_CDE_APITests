package projectroles;

import static dtomodels.projectroles.ProjectRolesTypeByRequestStructure.RANDOM_ROLE;

import basetests.StartTests;
import client.ProjectRolesClient;
import dtomodels.projectroles.ProjectRole;
import dtomodels.projectroles.ProjectRolesFactory;
import dtomodels.projectroles.ResponseProjectRole;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ProjectRolesBaseTests extends StartTests {

  protected static ProjectRolesClient projectRolesClient = new ProjectRolesClient();
  protected static ProjectRolesFactory rolesFactory = new ProjectRolesFactory();
  protected static ValidatableResponse createProjectRoleResponse;
  protected static ValidatableResponse getAllRolesResponse;
  protected static ValidatableResponse deleteResponse;
  protected static ProjectRole defaultProjectRole;
  protected static ResponseProjectRole defaultResponseProjectRole;
  protected static String defaultProjectRoleId;
  protected static List<ResponseProjectRole> allRolesList;
  protected static int projectRolesCount = 3;

  @BeforeAll
  public static void createDefaultProjectRole() {
    for (int i = 0; i < projectRolesCount; i++) {
      defaultProjectRole = new ProjectRolesFactory().createRole(RANDOM_ROLE);
      createProjectRoleResponse = projectRolesClient.createProjectRole(projectId,
          defaultProjectRole);
      defaultResponseProjectRole = createProjectRoleResponse.extract()
          .as(ResponseProjectRole.class);
      defaultProjectRoleId = defaultResponseProjectRole.getId();
    }
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
