package dtomodels.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectData {

  private String description;
  private String name;

  public ProjectData(String description, String name) {
    this.description = description;
    this.name = name;
  }

  public static ProjectData from(Project project) {
    return new ProjectData(project.getDescription(), project.getName());
  }

}
