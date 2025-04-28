package topicboardgroups;

import static dtomodels.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import basetests.StartTests;
import client.TopicBoardGroupsClient;
import dto.generated.CdeTopicBoardGroupDto;
import dto.generated.CdeCreateTopicBoardGroupDto;
import dtomodels.topicboardsgroup.TopicBoardsGroupFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardGroups(Группа досок задач)")
@Story("Получение группы досок задач в корне проекта")
public class GetTopicBoardGroupsOnProjectRootTests extends StartTests {

  private static ArrayList<CdeCreateTopicBoardGroupDto> topicBoardGroupsList = new ArrayList<>();
  private static List<CdeTopicBoardGroupDto> topicBoardGroupList;
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
    for (CdeCreateTopicBoardGroupDto cdeCreateTopicBoardGroupDto : topicBoardGroupsList) {
      topicBoardGroupsClient.createNewTopicBoardsGroup(projectId,
          cdeCreateTopicBoardGroupDto);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список групп досок и досок задач в корне проекта")
  public void getTopicBoardGroupsTest() {
    getTopicBoardGroupsResponse = topicBoardGroupsClient
        .getRootTopicBoardGroupsAndBoards(projectId);
    // todo создать доски и получить список этих досок тоже
    topicBoardGroupList = List.of(getTopicBoardGroupsResponse.extract().body()
        .as(CdeTopicBoardGroupDto[].class));
    statusCode = extractStatusCode(getTopicBoardGroupsResponse);

    assertEquals(countOfTopicBoardsGroup,
        topicBoardGroupList.size()); // fixme а если в проекте будут группы досок до нашего теста?
    assertNotNull(topicBoardGroupList);
  }

}
