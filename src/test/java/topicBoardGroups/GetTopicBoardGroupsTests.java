package topicBoardGroups;

import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import baseTests.StartTests;
import client.ProjectsClient;
import client.TopicBoardGroupsClients;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import models.project.Project;
import models.project.ProjectFactory;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.*;

public class GetTopicBoardGroupsTests extends StartTests {

  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ArrayList<TopicBoardsGroup> topicBoardGroupsList = new ArrayList<>();
  private static List<ResponseTopicBoardGroup> responseTopicBoardGroupList = new ArrayList<>();
  private static TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private static final int countOfTopicBoardsGroup = 5;

  private static String projectId;
  private static TopicBoardGroupsClients topicBoardGroupsClients = new TopicBoardGroupsClients();
  private ValidatableResponse getTopicBoardGroupsResponse;

  @BeforeAll
  @Step("Создать несколько групп досок задач")
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
    for (int i = 0; i < countOfTopicBoardsGroup; i++) {
      topicBoardGroupsList.add(topicBoardsGroupFactory
          .createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP));
    }
    for (TopicBoardsGroup topicBoardsGroup : topicBoardGroupsList) {
      topicBoardGroupsClients.createNewTopicBoardsGroup(projectId,
          topicBoardsGroup);
    }
  }

  @AfterAll
  @Step("Удалить проект")
  public static void deleteProject() {
    projectsClient.deleteProjectByItsId(projectId);
  }


  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список групп досок и досок задач в корне проекта")
  public void getTopicBoardGroupsTest() {
    getTopicBoardGroupsResponse = topicBoardGroupsClients
        .getRootTopicBoardGroupsAndBoards(projectId);
    // todo создать доски и получить список этих досок тоже
    responseTopicBoardGroupList = List.of(getTopicBoardGroupsResponse.extract().body()
        .as(ResponseTopicBoardGroup[].class));
    statusCode = extractStatusCode(getTopicBoardGroupsResponse);

    assertEquals(countOfTopicBoardsGroup, responseTopicBoardGroupList.size()); // fixme а если в проекте будут группы досок до нашего теста?
    assertNotNull(responseTopicBoardGroupList);

  }

}
