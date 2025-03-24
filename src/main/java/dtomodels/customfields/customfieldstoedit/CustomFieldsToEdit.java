package dtomodels.customfields.customfieldstoedit;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomFieldsToEdit {

  public ArrayList<CustomFieldToEdit> customFields;

  public CustomFieldsToEdit(CustomFieldToEdit customField) {
    this.customFields = new ArrayList<>();
    this.customFields.add(customField);
  }

  public void addCustomFieldToEdit(CustomFieldToEdit customField) {
    this.customFields.add(customField);
  }

}
