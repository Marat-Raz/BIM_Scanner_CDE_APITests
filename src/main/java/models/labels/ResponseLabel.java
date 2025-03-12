package models.labels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseLabel {

  public String id;
  public String name;
  public String color;
  public boolean isDeleted;

}
