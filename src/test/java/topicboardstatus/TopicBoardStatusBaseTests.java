package topicboardstatus;

import static dtomodels.statuses.StatusesType.DEFAULT;
import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;

import basetests.StartTests;
import client.TopicBoardStatusClient;
import client.TopicBoardsClient;
import dto.generated.CdeCreateOrUpdateTopicBoardStatusDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeTopicBoardStatusDto;
import dtomodels.statuses.StatusesFactory;
import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeCreateTopicBoardDto;
import dtomodels.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicBoardStatusBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static TopicBoardStatusClient topicBoardStatusClient = new TopicBoardStatusClient();
  protected static StatusesFactory statusesFactory = new StatusesFactory();
  protected static CdeCreateTopicBoardDto topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static String topicBoardId;
  protected ValidatableResponse addStatusResponse;
  protected CdeCreateOrUpdateTopicBoardStatusDto status;
  protected String statusId;
  protected CdeTopicBoardStatusDto cdeTopicBoardStatusDto;

  @BeforeAll
  @Step("Создаем доску задач в проекте")
  public static void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    CdeTopicBoardDto cdeTopicBoardDto =
        createTopicBoardsResponse.extract().as(CdeTopicBoardDto.class);
    topicBoardId = cdeTopicBoardDto.getId();
  }

  @BeforeEach
  @Step("Добавляем статусы в доску задач")
  public void addStatus() {
    status = statusesFactory.createStatuses(DEFAULT);
    addStatusResponse = topicBoardStatusClient.addTopicBoardStatuses(topicBoardId, status);
    cdeTopicBoardStatusDto = addStatusResponse.extract().as(CdeTopicBoardStatusDto.class);
    statusId = cdeTopicBoardStatusDto.getId();
  }

}
