package dtomodels.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseModel {

  public String id;
  public String name;
  public boolean isDeleted;

}
