package models.topicboards;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.customfields.ResponseCustomField;

@AllArgsConstructor
@Getter
@Setter
public class ResponseTopicBoards {

  public String type;
  public String id;
  public String projectId;
  public String parentGroupId;
  public String name;
  public String description;
  public ArrayList<ResponseCustomField> customFields;

}
