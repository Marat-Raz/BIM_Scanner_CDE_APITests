package dtomodels.projectroles;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectRole {

  public String name;
  public Boolean isDefault;
  public List<String> permissions;

}
