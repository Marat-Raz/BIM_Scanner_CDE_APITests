package dtomodels.topics;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
  //public String dueDate;
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

  public OffsetDateTime dueDate;

  public String getDueDate() {
    return dueDate.toString(); // ISO 8601 формат по умолчанию
  }

  public void setDueDate(String dueDate) {
    this.dueDate = OffsetDateTime.parse(dueDate);
  }
/*
  public String getDueDate() {
    return String (new LocalDateTime(dueDate)
        .atOffset(ZoneOffset.UTC)
        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        .replace("+00:00", "Z"));
  }


*//*  private String getDueDate() {
    return dueDate;
  }*/
}
