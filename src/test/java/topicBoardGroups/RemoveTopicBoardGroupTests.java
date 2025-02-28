package topicBoardGroups;

import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardGroupsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RemoveTopicBoardGroupTests extends StartTests {

  private static TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private static TopicBoardsGroup topicBoardsGroup;
  private static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private static ValidatableResponse createTopicBoardsGroupResponse;
  private static String topicBoardsGroupId;
  private ValidatableResponse deleteTopicBoardGroupResponse;

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
  @DisplayName("Удалить группу досок задач по его id")
  public void updateTopicBoardGroupByIdTest() {
    deleteTopicBoardGroupResponse = topicBoardGroupsClient
        .deleteTopicBoardGroup(projectId, topicBoardsGroupId);
    statusCode = extractStatusCode(deleteTopicBoardGroupResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
