package dtomodels.projectroles.projectrolestoedit;

import dtomodels.projectroles.ResponseProjectRole;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectRoleToEdit {

  private String name;
  private Boolean isDefault;
  private String concurrencyStamp;
  private List<String> permissions;

  public static ProjectRoleToEdit from(ResponseProjectRole responseProjectRole) {
    return new ProjectRoleToEdit(
        responseProjectRole.getName(),
        responseProjectRole.getIsDefault(),
        responseProjectRole.getConcurrencyStamp(),
        responseProjectRole.getPermissions()
    );}
}
