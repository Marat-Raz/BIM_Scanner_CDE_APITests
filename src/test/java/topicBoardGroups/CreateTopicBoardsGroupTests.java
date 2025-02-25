package topicBoardGroups;

import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.ProjectsClient;
import client.TopicBoardGroupsClients;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectFactory;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateTopicBoardsGroupTests extends StartTests {

  private TopicBoardGroupsClients topicBoardGroupsClients = new TopicBoardGroupsClients();
  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static String projectId;
  private TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private TopicBoardsGroup topicBoardsGroup;
  private ValidatableResponse createTopicBoardsGroupResponse;

  @BeforeAll
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать группу досок задач в корне проекта")
  public void createTopicBoardsGroupTest() {
    topicBoardsGroup = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClients.createNewTopicBoardsGroup(projectId,
        topicBoardsGroup);
    statusCode = extractStatusCode(createTopicBoardsGroupResponse);
    ResponseTopicBoardGroup responseTopicBoardGroup =
        createTopicBoardsGroupResponse.extract().as(ResponseTopicBoardGroup.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(responseTopicBoardGroup.type, "TopicBoardGroup"),
        () -> assertEquals(responseTopicBoardGroup.projectId, projectId),
        () -> assertEquals(responseTopicBoardGroup.projectId, projectId)
    );
  }

  // todo реализовать тесты из TestIT

}
