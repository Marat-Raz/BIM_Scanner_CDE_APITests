package dtomodels.project;

import dto.generated.CdeCreateProjectDto;
import java.time.LocalDateTime;
import dtomodels.RandomWord;

public class ProjectFactory {

  private String name = RandomWord.randomAllCharacters(1, 256);
  private String description = RandomWord.randomAllCharacters(1, 1000);
  private String completionTime = LocalDateTime.now().plusDays(14).toString();

  public CdeCreateProjectDto createProject(ProjectType projectType) {
    switch (projectType) {
      case PROJECT_WITHOUT_NAME:
        return new CdeCreateProjectDto(null, description, completionTime);
      case PROJECT_WITHOUT_DATA:
        return new CdeCreateProjectDto(null, null, null);
      case RANDOM_PROJECT:
        return new CdeCreateProjectDto(new RandomWord().randomAllCharacters(1, 256), description, completionTime);
      case DEFAULT_PROJECT:
      default:
        return new CdeCreateProjectDto(name, description, completionTime);
    }
  }
}
