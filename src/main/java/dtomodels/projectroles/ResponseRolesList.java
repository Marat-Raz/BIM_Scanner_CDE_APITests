package dtomodels.projectroles;

import dto.generated.CdeProjectRoleDto;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseRolesList {

  private ArrayList<CdeProjectRoleDto> roles;

}
/* todo
public class CustomFieldList<T> {

  public ArrayList<T> fields;

  public CustomFieldList(T field) {
    this.fields = new ArrayList<>();
    this.fields.add(field);
  }

  public void addField(T field) {
    this.fields.add(field);
  }
}
 */