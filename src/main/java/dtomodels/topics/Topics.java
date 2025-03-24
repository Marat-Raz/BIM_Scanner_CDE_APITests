package dtomodels.topics;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import dtomodels.customfields.ListOfCustomField;

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
  public ListOfCustomField customFields;
  public ArrayList<String> labels;

}

