package models.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Project {

  String name;
  String description;
  String imageUrl;
  String responsibleId;
  String completionTime;

}
