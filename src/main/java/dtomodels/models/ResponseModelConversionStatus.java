package dtomodels.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseModelConversionStatus {

  public String id;
  public String modelId;
  public Integer version;
  public JobStatus status;
  public Integer progress;
  public String error;
  public String createdAt;
  public String startedAt;
  public String completedAt;

}
