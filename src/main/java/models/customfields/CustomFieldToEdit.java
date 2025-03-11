package models.customfields;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import models.customfields.enumerationitem.ResponseEnumerationItem;

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
