package topics;

import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static dtomodels.topics.TopicType.DEFAULT_TOPIC;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.PaginatedResponse;
import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeCreateTopicBoardDto;
import dtomodels.topicboards.TopicBoardsFactory;
import dto.generated.CdeTopicDetailsDto;
import dto.generated.CdeCreateTopicDto;
import dtomodels.topics.TopicsFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Topics(Задачи)")
@Story("Получение всех задачи из проекта")
public class GetCdeCreateTopicDtoInCdeCreateProjectDtoTests extends CdeCreateTopicDtoBaseTests {

  private static CdeCreateTopicDto newTopic;
  private static ValidatableResponse createTopicBoardsResponse;
  private ValidatableResponse getListOfTopicsFromProjectResponse;

  @BeforeEach
  public void createNewTopicBoardAndNewTopic() {
    CdeCreateTopicBoardDto newTopicBoard = new TopicBoardsFactory().createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, newTopicBoard);
    CdeTopicBoardDto cdeTopicBoardDto =
        createTopicBoardsResponse.extract().as(CdeTopicBoardDto.class);
    newTopic = new TopicsFactory().createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicOnTopicBoard(cdeTopicBoardDto.getId(), newTopic);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все задачи из проекта")
  public void getTopicsInProjectTest() {
    getListOfTopicsFromProjectResponse = topicsClient
        .getTopicsInProjectWithoutQueryOptions(projectId);
    statusCode = extractStatusCode(getListOfTopicsFromProjectResponse);
    PaginatedResponse<CdeTopicDetailsDto> paginatedResponse = getListOfTopicsFromProjectResponse
        .extract().as(PaginatedResponse.class);
    ArrayList<CdeTopicDetailsDto> arrayOfTopics = paginatedResponse.getItems();

    assertEquals(SC_OK, statusCode);
    assertEquals(paginatedResponse.getTotalCount(),
        arrayOfTopics.size()); // fixme нужно другим образом сравнить
  }
}
