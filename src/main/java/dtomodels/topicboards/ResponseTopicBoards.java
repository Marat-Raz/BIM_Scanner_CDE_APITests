package dtomodels.topicboards;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import dtomodels.customfields.customfieldsintopicbords.CustomFieldOnTopicBoards;

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
  public ArrayList<CustomFieldOnTopicBoards> customFields;

}
