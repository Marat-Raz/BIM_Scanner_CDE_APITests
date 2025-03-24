package dtomodels.customfields;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import dtomodels.customfields.enumerationitem.EnumerationItem;

@AllArgsConstructor
@Getter
@Setter
public class CustomField {

  public String name;
  public String description;
  public CustomFieldType type;
  public List<EnumerationItem> enumerationItems;

}
