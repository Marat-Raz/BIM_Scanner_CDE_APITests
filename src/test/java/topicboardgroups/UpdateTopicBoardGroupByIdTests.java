package topicboardgroups;

import static dtomodels.topicboardsgroup.TopicBoardsGroupType.DEFAULT_TOPIC_BOARDS_GROUP;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardGroups(Группа досок задач)")
@Story("Редактирование группы досок задач")
public class UpdateTopicBoardGroupByIdTests extends StartTests {

  private TopicBoardsGroupFactory topicBoardsGroupFactory = new TopicBoardsGroupFactory();
  private CdeCreateTopicBoardGroupDto createTopicBoardGroup;
  private TopicBoardGroupsClient topicBoardGroupsClient = new TopicBoardGroupsClient();
  private ValidatableResponse createTopicBoardsGroupResponse;
  private ValidatableResponse updateTopicBoardGroupResponse;
  private String topicBoardsGroupId;

  @BeforeEach
  @Step("Создать  в проекте группу досок задач")
  public void createTopicBoardGroupsOnProject() {
    createTopicBoardGroup = topicBoardsGroupFactory.createTopicBoardsGroup(DEFAULT_TOPIC_BOARDS_GROUP);
    createTopicBoardsGroupResponse = topicBoardGroupsClient.createNewTopicBoardsGroup(projectId,
        createTopicBoardGroup);
    topicBoardsGroupId = createTopicBoardsGroupResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить группу досок задач по его id")
  public void updateTopicBoardGroupByIdTest() {
    CdeCreateTopicBoardGroupDto newCdeCreateTopicBoardGroupDto = createTopicBoardGroup;
    newCdeCreateTopicBoardGroupDto.setName("newName");
    updateTopicBoardGroupResponse = topicBoardGroupsClient
        .updateTopicBoardGroup(projectId, topicBoardsGroupId, newCdeCreateTopicBoardGroupDto);
    statusCode = extractStatusCode(updateTopicBoardGroupResponse);
    CdeTopicBoardGroupDto cdeTopicBoardGroupDto = updateTopicBoardGroupResponse
        .extract().as(CdeTopicBoardGroupDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(newCdeCreateTopicBoardGroupDto.getName(), cdeTopicBoardGroupDto.getName()),
        () -> assertEquals(topicBoardsGroupId, cdeTopicBoardGroupDto.getId()),
        () -> assertEquals(projectId, cdeTopicBoardGroupDto.getProjectId())
    );
  }

}