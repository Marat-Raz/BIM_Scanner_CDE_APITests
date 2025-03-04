package topicgoardgroups;

import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.TopicBoardGroupsClient;
import io.restassured.response.ValidatableResponse;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateTopicBoardsGroupTests extends StartTests {

  private TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private TopicBoardsGroup topicBoardsGroup;
  private ValidatableResponse createTopicBoardsGroupResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать группу досок задач в корне проекта")
  public void createTopicBoardsGroupTest() {
    topicBoardsGroup = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClient.createNewTopicBoardsGroup(projectId,
        topicBoardsGroup);
    statusCode = extractStatusCode(createTopicBoardsGroupResponse);
    ResponseTopicBoardGroup responseTopicBoardGroup =
        createTopicBoardsGroupResponse.extract().as(ResponseTopicBoardGroup.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals("TopicBoardGroup", responseTopicBoardGroup.type),
        () -> assertEquals(topicBoardsGroup.getName(), responseTopicBoardGroup.name),
        () -> assertEquals(projectId, responseTopicBoardGroup.projectId)
    );
  }

  // todo реализовать тесты из TestIT

}
