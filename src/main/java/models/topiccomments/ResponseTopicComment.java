package models.topiccomments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseTopicComment {

  public String id;
  public String creationTime;
  public String creatorId;
  public String lastModificationTime;
  public String lastModifierId;
  public String topicId;
  public String comment;

}
