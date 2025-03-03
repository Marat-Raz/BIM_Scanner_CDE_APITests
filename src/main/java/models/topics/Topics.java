package models.topics;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.customfields.CustomFields;

@AllArgsConstructor
@Getter
@Setter
public class Topics {

  public String title;
  public String description;
  public String dueDate;
  public String assignedToId;
  public String typeId;
  public String statusId;
  public String priorityId;
  public CustomFields customFields;
  public ArrayList<String> labels;

}

