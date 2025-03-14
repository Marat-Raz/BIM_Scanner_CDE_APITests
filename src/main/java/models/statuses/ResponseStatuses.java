package models.statuses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseStatuses {

  public String id;
  public String name;
  public String color;

}
