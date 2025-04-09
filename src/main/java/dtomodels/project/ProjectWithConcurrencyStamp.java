package dtomodels.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectWithConcurrencyStamp {

  private String name;
  private String description;
  private String responsibleId;
  private String completionTime;
  private String concurrencyStamp;

  public ProjectWithConcurrencyStamp(Project project, String responsibleId, String concurrencyStamp) {
    name = project.getName();
    description = project.getDescription();
    this.responsibleId = responsibleId;
    completionTime = project.getCompletionTime();
    this.concurrencyStamp = concurrencyStamp;
  }
}

