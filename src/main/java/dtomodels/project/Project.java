package dtomodels.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Project {

  private String name;
  private String description;
  private String responsibleId;
  private String completionTime;

}
