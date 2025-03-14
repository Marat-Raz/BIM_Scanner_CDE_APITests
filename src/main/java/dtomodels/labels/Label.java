package dtomodels.labels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Label {

  public String id;
  public String name;
  public String color;
  public boolean isDeleted;

}
