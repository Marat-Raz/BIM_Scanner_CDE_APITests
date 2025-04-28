package topicboardgroups;

import static dtomodels.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;

import basetests.StartTests;
import client.TopicBoardGroupsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeTopicBoardGroupDto;
import dto.generated.CdeCreateTopicBoardGroupDto;
import dtomodels.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.BeforeAll;

public class TopicBoardGroupBaseTests extends StartTests {

  protected static CdeCreateTopicBoardGroupDto cdeCreateTopicBoardGroupDto;
  protected static TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  protected static ValidatableResponse createTopicBoardsGroupResponse;
  protected static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  protected static CdeTopicBoardGroupDto topicBoardGroup;
  protected static String topicBoardsGroupId;

  @BeforeAll
  @Step("Создать в проекте группу досок задач")
  public static void createBoardsGroup() {
    cdeCreateTopicBoardGroupDto = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClient
        .createNewTopicBoardsGroup(projectId, cdeCreateTopicBoardGroupDto);
    topicBoardGroup =
        createTopicBoardsGroupResponse.extract().as(CdeTopicBoardGroupDto.class);
    topicBoardsGroupId = topicBoardGroup.getId();
  }
}
