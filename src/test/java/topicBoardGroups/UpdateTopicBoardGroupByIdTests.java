package topicBoardGroups;

import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.ProjectsClient;
import client.TopicBoardGroupsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectFactory;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.*;

public class UpdateTopicBoardGroupByIdTests extends StartTests {

  private static TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private static TopicBoardsGroup topicBoardsGroup;
  private static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private static ValidatableResponse createTopicBoardsGroupResponse;
  private ValidatableResponse updateTopicBoardGroupResponse;
  static String topicBoardsGroupId;

  @BeforeEach
  @Step("Создать  в проекте группу досок задач")
  public void createTopicBoardGroupsOnProject() {
    topicBoardsGroup = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClient.createNewTopicBoardsGroup(projectId,
        topicBoardsGroup);
    topicBoardsGroupId = createTopicBoardsGroupResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить группу досок задач по его id")
  public void updateTopicBoardGroupByIdTest() {
    TopicBoardsGroup newTopicBoardsGroup = topicBoardsGroup;
    newTopicBoardsGroup.setName("newName");
    updateTopicBoardGroupResponse = topicBoardGroupsClient
        .updateTopicBoardGroup(projectId, topicBoardsGroupId, newTopicBoardsGroup);
    statusCode = extractStatusCode(updateTopicBoardGroupResponse);
    ResponseTopicBoardGroup responseTopicBoardGroup = updateTopicBoardGroupResponse
        .extract().as(ResponseTopicBoardGroup.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(newTopicBoardsGroup.getName(), responseTopicBoardGroup.getName()),
        () -> assertEquals(topicBoardsGroupId, responseTopicBoardGroup.getId()),
        () -> assertEquals(projectId, responseTopicBoardGroup.projectId)
    );
  }

}