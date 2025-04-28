package dtomodels.projectroles;

import dto.generated.CdeCreateProjectRoleDto;
import dtomodels.RandomWord;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProjectRolesFactory {

  private String name = RandomWord.randomLatinAndNumberCharacters(1, 256);
  private ArrayList<String> permissions = new ArrayList<>(List.of("Project.Update"));
  private ArrayList<String> randomPermissions = new ArrayList<>(List.of(getRandomPermission().toString()));

  public CdeCreateProjectRoleDto createRole(ProjectRolesTypeByRequestStructure roleType) {
    switch (roleType) {
      case ROLE_WITHOUT_NAME:
        return new CdeCreateProjectRoleDto(null, true, permissions);
      case ROLE_WITHOUT_IS_DEFAULT:
        return new CdeCreateProjectRoleDto(name, null, permissions);
      case ROLE_WITHOUT_PERMISSIONS:
        return new CdeCreateProjectRoleDto(name, true, null);
      case RANDOM_ROLE:
        return new CdeCreateProjectRoleDto(name, true, randomPermissions);
      case DEFAULT_ROLE:
      default:
        return new CdeCreateProjectRoleDto(name, true, permissions);
    }
  }

  private static ProjectPermissions getRandomPermission() {
    ProjectPermissions[] permissions = ProjectPermissions.values();
    int randomIndex = new Random().nextInt(permissions.length);
    return permissions[randomIndex];
  }
}
