package topicboardgroups;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicBoardGroupsClient;
import dto.generated.CdeTopicBoardGroupDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardGroups(Группа досок задач)")
@Story("Получение группы досок задач по id")
public class GetTopicBoardGroupByIdTests extends TopicBoardGroupBaseTests {

  private static List<CdeTopicBoardGroupDto> cdeTopicBoardGroupDtoList;
  private static TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private ValidatableResponse getTopicBoardGroupResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить группу досок задач по его id, включая её содержимое")
  public void getTopicBoardGroupByIdTest() {
    getTopicBoardGroupResponse = topicBoardGroupsClient
        .getTopicBoardGroupById(projectId, topicBoardsGroupId, false);
    statusCode = extractStatusCode(getTopicBoardGroupResponse);
    cdeTopicBoardGroupDtoList = List.of(getTopicBoardGroupResponse.extract()
        .as(CdeTopicBoardGroupDto[].class));

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals("TopicBoardGroup", cdeTopicBoardGroupDtoList.get(0).getType()),
        () -> assertEquals(cdeCreateTopicBoardGroupDto.getName(), cdeTopicBoardGroupDtoList.get(0).getName()),
        () -> assertEquals(projectId, cdeTopicBoardGroupDtoList.get(0).getProjectId())
    );
  }

}