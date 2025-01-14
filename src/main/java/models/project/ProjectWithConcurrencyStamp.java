package models.project;

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

  public ProjectWithConcurrencyStamp(Project project, String concurrencyStamp) {
    this.name = project.getName();
    this.description = project.getDescription();
    this.responsibleId = project.getResponsibleId();
    this.completionTime = project.getCompletionTime();
    this.concurrencyStamp = concurrencyStamp;
  }
}

