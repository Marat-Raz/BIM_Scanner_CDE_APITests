package dtomodels.comment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ResponseTopicComment {

  public String id;
  public String creationTime;
  public String creatorId;
  public String lastModificationTime;
  public String lastModifierId;
  public String topicId;
  public String comment;

}
