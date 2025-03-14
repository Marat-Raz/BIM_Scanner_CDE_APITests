package dtomodels.topics;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import dtomodels.customfields.CustomField;
import dtomodels.labels.Label;
import dtomodels.priorities.Priorities;
import dtomodels.statuses.Statuses;
import dtomodels.types.Types;

@AllArgsConstructor
@Getter
@Setter
public class ResponseTopics {

  public String serverAssignedId;
  public String title;
  public String description;
  public String dueDate;
  public String assignedToId;
  public String topicBoardId;
  public String concurrencyStamp;
  public Types type;
  public Statuses status;
  public Priorities priority;
  public ArrayList<CustomField> customFields;
  public ArrayList<Label> labels;
  public String lastModificationTime;
  public String lastModifierId;
  public String creationTime;
  public String creatorId;
  public String id;

}
