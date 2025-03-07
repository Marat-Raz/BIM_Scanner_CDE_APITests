package topicgoardgroups;

import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.TopicBoardGroupsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.*;

public class UpdateTopicBoardGroupByIdTests extends StartTests {

  private TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private TopicBoardsGroup topicBoardsGroup;
  private TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private ValidatableResponse createTopicBoardsGroupResponse;
  private ValidatableResponse updateTopicBoardGroupResponse;
  private String topicBoardsGroupId;

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