package topicboardgroups;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicBoardGroupsClient;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import dtomodels.topicboardsgroup.ResponseTopicBoardGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardGroupByIdTests extends TopicBoardGroupBaseTests {

  private static List<ResponseTopicBoardGroup> responseTopicBoardGroupList;
  private static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private ValidatableResponse getTopicBoardGroupResponse;

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