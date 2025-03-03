package topicboardtypes;

import static models.types.TypesType.DEFAULT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardTypesClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import models.types.ResponseTypes;
import models.types.Types;
import models.types.TypesFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddTypeToTopicBoardTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private ValidatableResponse addTypesResponse;
  private TopicBoardTypesClient topicBoardTypesClient = new TopicBoardTypesClient();
  private TypesFactory typesFactory = new TypesFactory();
  private Types type;

  @BeforeAll
  public static void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать статус в доске задач")
  public void addTypesToTopicBoardTest() {
    type = typesFactory.createTypes(DEFAULT);
    addTypesResponse = topicBoardTypesClient.addTypesToTopicBoard(topicBoardId, type);
    statusCode = extractStatusCode(addTypesResponse);
    ResponseTypes responseTypes = addTypesResponse.extract().as(ResponseTypes.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(type.getName(), responseTypes.getName()),
        () -> assertEquals(type.getColor(), responseTypes.getColor())
    );
  }
}

