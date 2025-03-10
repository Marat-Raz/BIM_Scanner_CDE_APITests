package topicboardgroups;

import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import basetests.StartTests;
import client.TopicBoardGroupsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardGroupsOnProjectRootTests extends StartTests {

  private static ArrayList<TopicBoardsGroup> topicBoardGroupsList = new ArrayList<>();
  private static List<ResponseTopicBoardGroup> responseTopicBoardGroupList;
  private static final int countOfTopicBoardsGroup = 5;
  private static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private ValidatableResponse getTopicBoardGroupsResponse;

  @BeforeEach
  @Step("Создать  в проекте несколько групп досок задач")
  public void createTopicBoardGroupsOnProject() {
    for (int i = 0; i < countOfTopicBoardsGroup; i++) {
      topicBoardGroupsList.add(new TopicBoardsGroupFactory()
          .createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP));
    }
    for (TopicBoardsGroup topicBoardsGroup : topicBoardGroupsList) {
      topicBoardGroupsClient.createNewTopicBoardsGroup(projectId,
          topicBoardsGroup);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список групп досок и досок задач в корне проекта")
  public void getTopicBoardGroupsTest() {
    getTopicBoardGroupsResponse = topicBoardGroupsClient
        .getRootTopicBoardGroupsAndBoards(projectId);
    // todo создать доски и получить список этих досок тоже
    responseTopicBoardGroupList = List.of(getTopicBoardGroupsResponse.extract().body()
        .as(ResponseTopicBoardGroup[].class));
    statusCode = extractStatusCode(getTopicBoardGroupsResponse);

    assertEquals(countOfTopicBoardsGroup,
        responseTopicBoardGroupList.size()); // fixme а если в проекте будут группы досок до нашего теста?
    assertNotNull(responseTopicBoardGroupList);
  }

}
