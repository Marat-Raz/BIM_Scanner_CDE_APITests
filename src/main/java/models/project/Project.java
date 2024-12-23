package models.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Project {

  private String name, description, imageUrl, responsibleId, completionTime;

}