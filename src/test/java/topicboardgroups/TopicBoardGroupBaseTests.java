package topicboardgroups;

import static dtomodels.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;

import basetests.StartTests;
import client.TopicBoardGroupsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dtomodels.topicboardsgroup.ResponseTopicBoardGroup;
import dtomodels.topicboardsgroup.TopicBoardsGroup;
import dtomodels.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.BeforeAll;

public class TopicBoardGroupBaseTests extends StartTests {

  protected static TopicBoardsGroup topicBoardsGroup;
  protected static TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  protected static ValidatableResponse createTopicBoardsGroupResponse;
  protected static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  protected static ResponseTopicBoardGroup responseTopicBoardGroup;
  protected static String topicBoardsGroupId;

  @BeforeAll
  @Step("Создать в проекте группу досок задач")
  public static void createBoardsGroup() {
    topicBoardsGroup = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClient
        .createNewTopicBoardsGroup(projectId, topicBoardsGroup);
    responseTopicBoardGroup =
        createTopicBoardsGroupResponse.extract().as(ResponseTopicBoardGroup.class);
    topicBoardsGroupId = responseTopicBoardGroup.getId();
  }
}
