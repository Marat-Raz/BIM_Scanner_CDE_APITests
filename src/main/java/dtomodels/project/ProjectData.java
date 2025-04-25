package dtomodels.project;

import dto.generated.CdeCreateProjectDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectData { // todo кандидат на удаление

  private String description;
  private String name;

  public ProjectData(String description, String name) {
    this.description = description;
    this.name = name;
  }

  public static ProjectData from(CdeCreateProjectDto cdeCreateProjectDto) {
    return new ProjectData(cdeCreateProjectDto.getDescription(), cdeCreateProjectDto.getName());
  }

}
