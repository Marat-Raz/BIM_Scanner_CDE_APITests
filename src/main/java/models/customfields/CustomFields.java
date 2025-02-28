package models.customfields;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomFields {

  public ArrayList<CustomField> customFields;

  public CustomFields(CustomField customField) {
    this.customFields = new ArrayList<>();
    this.customFields.add(customField);
  }

  public void addCustomField(CustomField customField) {
    this.customFields.add(customField);
  }
}
