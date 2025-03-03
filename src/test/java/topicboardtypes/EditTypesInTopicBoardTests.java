package topicboardtypes;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static models.types.TypesType.DEFAULT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardTypesClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import models.types.ResponseTypes;
import models.types.Types;
import models.types.TypesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class EditTypesInTopicBoardTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse addTypeResponse;
  private static ValidatableResponse editTypeResponse;
  private static TopicBoardTypesClient topicBoardTypeClient = new TopicBoardTypesClient();
  private static TypesFactory typesFactory = new TypesFactory();
  private static Types status;
  private static String statusId;

  @BeforeAll
  public static void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
    status = typesFactory.createTypes(DEFAULT);
    addTypeResponse = topicBoardTypeClient.addTypesToTopicBoard(topicBoardId, status);
    statusId = addTypeResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Редактировать параметр «name» статуса в доске задач")
  public void createTopicBoardsGroupTest() {
    Types editableType = status;
    editableType.setName("newName");
    editTypeResponse = topicBoardTypeClient
        .editTypesInTopicBoard(topicBoardId, statusId, editableType);
    statusCode = extractStatusCode(editTypeResponse);
    ResponseTypes editedTypeFromResponse =
        editTypeResponse.extract().as(ResponseTypes.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(editableType.getName(), editedTypeFromResponse.getName());
  }

}
