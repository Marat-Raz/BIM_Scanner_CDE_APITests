package topicBoardGroups;

import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.ProjectsClient;
import client.TopicBoardGroupsClients;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import models.project.Project;
import models.project.ProjectFactory;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.*;

public class UpdateTopicBoardGroupByIdTests extends StartTests {

  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static List<ResponseTopicBoardGroup> responseTopicBoardGroupList;
  private static TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private static TopicBoardsGroup topicBoardsGroup;
  private static String projectId;
  private static TopicBoardGroupsClients topicBoardGroupsClients = new TopicBoardGroupsClients();
  private static ValidatableResponse createTopicBoardsGroupResponse;
  private ValidatableResponse updateTopicBoardGroupResponse;
  static String topicBoardsGroupId;

  @BeforeAll
  @Step("Создать проект, в ней создать группу досок задач")
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
    topicBoardsGroup = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClients.createNewTopicBoardsGroup(projectId,
        topicBoardsGroup);
    topicBoardsGroupId = createTopicBoardsGroupResponse.extract().path("id");
  }

  @AfterAll
  @Step("Удалить проект")
  public static void deleteProject() {
    projectsClient.deleteProjectByItsId(projectId);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить группу досок задач по его id")
  public void updateTopicBoardGroupByIdTest() {
    TopicBoardsGroup newTopicBoardsGroup = topicBoardsGroup;
    newTopicBoardsGroup.setName("newName");
    updateTopicBoardGroupResponse = topicBoardGroupsClients
        .updateTopicBoardGroup(projectId, topicBoardsGroupId, newTopicBoardsGroup);
    statusCode = extractStatusCode(updateTopicBoardGroupResponse);
    ResponseTopicBoardGroup responseTopicBoardGroup = updateTopicBoardGroupResponse
        .extract().as(ResponseTopicBoardGroup.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(topicBoardsGroupId, responseTopicBoardGroup.getId());
    assertEquals(newTopicBoardsGroup.getName(), responseTopicBoardGroup.getName());
  }

}