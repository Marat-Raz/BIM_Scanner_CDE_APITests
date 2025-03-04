package topicgoardgroups;

import static models.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.TopicBoardGroupsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import models.topicboardsgroup.ResponseTopicBoardGroup;
import models.topicboardsgroup.TopicBoardsGroup;
import models.topicboardsgroup.TopicBoardsGroupFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardGroupByIdTests extends StartTests {

  private static List<ResponseTopicBoardGroup> responseTopicBoardGroupList;
  private static TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private static TopicBoardsGroup topicBoardsGroup;
  private static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private static ValidatableResponse createTopicBoardsGroupResponse;
  private ValidatableResponse getTopicBoardGroupResponse;
  static String topicBoardsGroupId;

  @BeforeAll
  @Step("Создать в проекте группу досок задач")
  public static void createProject() {
    topicBoardsGroup = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClient.createNewTopicBoardsGroup(projectId,
        topicBoardsGroup);
    topicBoardsGroupId = createTopicBoardsGroupResponse.extract().path("id");
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