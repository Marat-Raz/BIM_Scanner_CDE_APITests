package dtomodels.modelrevisions;

import dtomodels.user.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseModelRevisions {

  public String modelId;
  public int version;
  public String modelName;
  public String comment;
  public String creationTime;
  public Author createdBy;
  public String status;

}
