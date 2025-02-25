package topicBoardGroups;

import static models.project.ProjectType.DEFAULT_PROJECT;

import baseTests.StartTests;
import client.ProjectsClient;
import client.TopicBoardGroupsClients;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TopicBoardGroupsTests extends StartTests {
  private TopicBoardGroupsClients topicBoardGroupsClients = new TopicBoardGroupsClients();
  private ValidatableResponse response;
  private ProjectsClient projectsClient = new ProjectsClient();
  private ProjectFactory projectFactory = new ProjectFactory();
  private String projectId;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список групп досок и досок задач в корне проекта")
  public void TopicBoardGroupsTest() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");

    response = topicBoardGroupsClients.getRootTopicBoardGroupsAndBoards(projectId);
    // todo реализовать далее
  }


}
/*


 */