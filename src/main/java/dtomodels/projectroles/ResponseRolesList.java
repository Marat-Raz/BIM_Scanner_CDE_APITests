package dtomodels.projectroles;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseRolesList {

  private ArrayList<ResponseProjectRole> roles;

}
