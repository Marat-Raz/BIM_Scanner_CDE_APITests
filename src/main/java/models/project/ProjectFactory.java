package models.project;

import java.time.LocalDateTime;
import org.apache.commons.lang3.RandomStringUtils;

public class ProjectFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);
  private String description = RandomStringUtils.randomAlphabetic(0, 1000);
  private String imageUrl = "https://www.figma.com/design/aBrjGB38u4WJlGuccL7x9d/BRIO-MRS?node-id=153-8&p=f&t=Kon204ZOtgPlZuq3-0"; // todo удалить после внесения изменений в Swagger
  private String responsibleId;
  private String completionTime = LocalDateTime.now().plusDays(14).toString();

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
