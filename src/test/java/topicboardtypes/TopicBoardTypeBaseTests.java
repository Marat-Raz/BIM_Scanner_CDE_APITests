package topicboardtypes;

import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static dtomodels.types.TypesType.DEFAULT;

import basetests.StartTests;
import client.TopicBoardTypesClient;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeCreateTopicBoardDto;
import dtomodels.topicboards.TopicBoardsFactory;
import dto.generated.CdeTopicBoardTypeDto;
import dto.generated.CdeCreateOrUpdateTopicBoardTypeDto;
import dtomodels.types.TypesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicBoardTypeBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static TopicBoardTypesClient topicBoardTypesClient = new TopicBoardTypesClient();
  protected static TypesFactory typesFactory = new TypesFactory();
  protected static CdeCreateTopicBoardDto topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static String topicBoardId;
  protected ValidatableResponse addTypesResponse;
  protected CdeCreateOrUpdateTopicBoardTypeDto type;
  protected String typeId;
  protected CdeTopicBoardTypeDto cdeTopicBoardTypeDto;

  @BeforeAll
  @Step("Создаем доску задач в проекте")
  public static void createTopicBoardAndAddType() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    CdeTopicBoardDto cdeTopicBoardDto =
        createTopicBoardsResponse.extract().as(CdeTopicBoardDto.class);
    topicBoardId = cdeTopicBoardDto.getId();
  }

  @BeforeEach
  @Step("Добавляем типы в доску задач")
  public void addType() {
    type = typesFactory.createTypes(DEFAULT);
    addTypesResponse = topicBoardTypesClient.addTopicBoardTypes(topicBoardId, type);
    cdeTopicBoardTypeDto = addTypesResponse.extract().as(CdeTopicBoardTypeDto.class);
    typeId = cdeTopicBoardTypeDto.getId();
  }

}
