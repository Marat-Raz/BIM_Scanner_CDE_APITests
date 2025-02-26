package topicBoardGroups;

import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import baseTests.StartTests;
import client.ProjectsClient;
import client.TopicBoardGroupsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import models.project.Project;
import models.project.ProjectFactory;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.*;

public class GetTopicBoardGroupByIdTests extends StartTests {

  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static List<ResponseTopicBoardGroup> responseTopicBoardGroupList;
  private static TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private static TopicBoardsGroup topicBoardsGroup;
  private static String projectId;
  private static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private static ValidatableResponse createTopicBoardsGroupResponse;
  private ValidatableResponse getTopicBoardGroupResponse;
  static String topicBoardsGroupId;

  @BeforeAll
  @Step("Создать проект, в ней создать группу досок задач")
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
    topicBoardsGroup = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClient.createNewTopicBoardsGroup(projectId,
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
  @DisplayName("Получить группу досок задач по его id, включая её содержимое")
  public void getTopicBoardGroupByIdTest() {
    getTopicBoardGroupResponse = topicBoardGroupsClient
        .getTopicBoardGroupById(projectId, topicBoardsGroupId, false);
    statusCode = extractStatusCode(getTopicBoardGroupResponse);
    responseTopicBoardGroupList = List.of(getTopicBoardGroupResponse.extract()
        .as(ResponseTopicBoardGroup[].class));

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals("TopicBoardGroup", responseTopicBoardGroupList.get(0).type),
        () -> assertEquals(topicBoardsGroup.getName(), responseTopicBoardGroupList.get(0).name),
        () -> assertEquals(projectId, responseTopicBoardGroupList.get(0).projectId)
    );
  }

}