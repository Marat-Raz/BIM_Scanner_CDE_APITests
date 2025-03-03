package models.topics;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.customfields.CustomFields;
import models.labels.Label;
import models.priorities.ResponsePriorities;
import models.statuses.ResponseStatuses;
import models.types.ResponseTypes;

@AllArgsConstructor
@Getter
@Setter
public class ResponseTopics {

  public String id;
  public String creationTime;
  public String creatorId;
  public String lastModificationTime;
  public String lastModifierId;
  public String serverAssignedId;
  public String title;
  public String description;
  public String dueDate;
  public String assignedToId;
  public String topicBoardId;
  public String concurrencyStamp;
  public ResponseTypes type;
  public ResponseStatuses status;
  public ResponsePriorities priority;
  public CustomFields customFields;
  public ArrayList<Label> labels;

}
