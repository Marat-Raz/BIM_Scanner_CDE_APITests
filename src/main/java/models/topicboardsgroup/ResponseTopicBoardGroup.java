package models.topicboardsgroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseTopicBoardGroup {

  public String type;
  public String id;
  public String projectId;
  public String parentGroupId;
  public String name;

}
