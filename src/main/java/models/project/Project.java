package models.project;

import java.time.LocalDateTime;
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
  LocalDateTime completionTime;

}
