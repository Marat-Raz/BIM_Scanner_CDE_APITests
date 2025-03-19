package dtomodels.customfields.customfieldstoedit;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import dtomodels.customfields.ResponseCustomField;
import dtomodels.customfields.enumerationitem.ResponseEnumerationItem;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomFieldToEdit {

  public String name;
  public String description;
  public boolean archived;
  public ArrayList<ResponseEnumerationItem> enumerationItems;

  public static CustomFieldToEdit from(ResponseCustomField responseCustomField) {
    return new CustomFieldToEdit(responseCustomField.getName(), responseCustomField.description,
        responseCustomField.isArchived(), responseCustomField.getEnumerationItems());
  }

}
