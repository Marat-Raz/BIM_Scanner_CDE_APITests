package topicboardtypes;

import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static dtomodels.types.TypesType.DEFAULT;

import basetests.StartTests;
import client.TopicBoardTypesClient;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dtomodels.topicboards.ResponseTopicBoards;
import dtomodels.topicboards.TopicBoards;
import dtomodels.topicboards.TopicBoardsFactory;
import dtomodels.types.ResponseTypes;
import dtomodels.types.Types;
import dtomodels.types.TypesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicBoardTypeBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static TopicBoardTypesClient topicBoardTypesClient = new TopicBoardTypesClient();
  protected static TypesFactory typesFactory = new TypesFactory();
  protected static TopicBoards topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static String topicBoardId;
  protected ValidatableResponse addTypesResponse;
  protected Types type;
  protected String typeId;
  protected ResponseTypes responseTypes;

  @BeforeAll
  @Step("Создаем доску задач в проекте")
  public static void createTopicBoardAndAddType() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
  }

  @BeforeEach
  @Step("Добавляем типы в доску задач")
  public void addType() {
    type = typesFactory.createTypes(DEFAULT);
    addTypesResponse = topicBoardTypesClient.addTopicBoardTypes(topicBoardId, type);
    responseTypes = addTypesResponse.extract().as(ResponseTypes.class);
    typeId = responseTypes.getId();
  }

}
