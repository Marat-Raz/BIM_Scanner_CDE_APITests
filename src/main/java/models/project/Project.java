package models.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Project {

  protected String name;
  protected String description;
  protected String responsibleId;
  protected String completionTime;

}
