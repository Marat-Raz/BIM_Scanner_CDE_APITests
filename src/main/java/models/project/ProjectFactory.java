package models.project;

import java.time.LocalDateTime;
import org.apache.commons.lang3.RandomStringUtils;

public class ProjectFactory {

  private String name = RandomStringUtils.randomAlphabetic(6, 256);
  private String description = RandomStringUtils.randomAlphabetic(3, 247) + "@mail.com";
  private String imageUrl = RandomStringUtils.randomAlphabetic(0, 256);
  private String responsibleId = RandomStringUtils.randomAlphabetic(8);
  private LocalDateTime completionTime = LocalDateTime.now().plusDays(14);

  public Project createProject(ProjectType projectType) {
    switch (projectType) {
      case PROJECT_WITHOUT_NAME:
        return new Project(null, description, imageUrl, responsibleId, completionTime);
      case PROJECT_WITH_NULL:
        return new Project(null, null, null, null, null);
      default:
      case DEFAULT_PROJECT:
        return new Project(name, description, imageUrl, responsibleId, completionTime);
    }
  }
}
