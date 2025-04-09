package topicboardgroups;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardGroups(Группа досок задач)")
@Story("Редактирование группы досок задач")
public class RemoveTopicBoardGroupTests extends TopicBoardGroupBaseTests {

  private ValidatableResponse deleteTopicBoardGroupResponse;

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
