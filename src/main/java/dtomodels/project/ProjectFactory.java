package dtomodels.project;

import java.time.LocalDateTime;
import org.apache.commons.lang3.RandomStringUtils;

public class ProjectFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);
  private String description = new String(RandomStringUtils.randomAlphabetic(0, 1000));
  private String responsibleId;
  private String completionTime = LocalDateTime.now().plusDays(14).toString();

  public Project createProject(ProjectType projectType) {
    switch (projectType) {
      case PROJECT_WITHOUT_NAME:
        return new Project(null, description, responsibleId, completionTime);
      case PROJECT_WITHOUT_DATA:
        return new Project( null, null, null, null);
      case RANDOM_PROJECT:
        return new Project(RandomStringUtils.randomAlphabetic(1, 256), description, responsibleId,
            completionTime);
      case DEFAULT_PROJECT:
      default:
        return new Project(name, description, responsibleId, completionTime);
    }
  }
}
