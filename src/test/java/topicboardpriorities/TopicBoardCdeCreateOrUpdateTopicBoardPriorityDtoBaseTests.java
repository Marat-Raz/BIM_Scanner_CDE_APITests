package topicboardpriorities;

import static dtomodels.priorities.PrioritiesType.DEFAULT;
import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;

import basetests.StartTests;
import client.TopicBoardPrioritiesClient;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeCreateOrUpdateTopicBoardPriorityDto;
import dtomodels.priorities.PrioritiesFactory;
import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeCreateTopicBoardDto;
import dtomodels.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicBoardCdeCreateOrUpdateTopicBoardPriorityDtoBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static TopicBoardPrioritiesClient topicBoardPrioritiesClient
      = new TopicBoardPrioritiesClient();
  protected static PrioritiesFactory prioritiesFactory = new PrioritiesFactory();
  protected static CdeCreateTopicBoardDto topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static String topicBoardId;
  protected ValidatableResponse addPrioritiesResponse;
  protected CdeCreateOrUpdateTopicBoardPriorityDto priority;
  protected String priorityId;

  @BeforeAll
  @Step("Создать в проекте доску задач")
  public static void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    CdeTopicBoardDto cdeTopicBoardDto =
        createTopicBoardsResponse.extract().as(CdeTopicBoardDto.class);
    topicBoardId = cdeTopicBoardDto.getId();
  }

  @BeforeEach
  @Step("Добавить в доску задач приоритеты")
  public void addPrioritiesToTopicBoard() {
    priority = prioritiesFactory.createPriorities(DEFAULT);
    addPrioritiesResponse = topicBoardPrioritiesClient.addTopicBoardPriorities(topicBoardId,
        priority);
    priorityId = addPrioritiesResponse.extract().path("id");
  }

}
