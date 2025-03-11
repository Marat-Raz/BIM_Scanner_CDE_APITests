package models.customfields.customfieldsintopicbords;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomFieldsOnTopicBoards {

  public ArrayList<CustomFieldOnTopicBoards> customFields;

  public CustomFieldsOnTopicBoards(CustomFieldOnTopicBoards customField) {
    this.customFields = new ArrayList<>();
    this.customFields.add(customField);
  }

  public void addCustomFieldToEdit(CustomFieldOnTopicBoards customField) {
    this.customFields.add(customField);
  }

}
