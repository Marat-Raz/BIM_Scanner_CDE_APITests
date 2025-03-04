package topicboardtypes;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static models.types.TypesType.DEFAULT;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.TopicBoardTypesClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import models.types.Types;
import models.types.TypesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteTypesFromTopicBoardTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse addTypesResponse;
  private static ValidatableResponse deleteTypesResponse;
  private static TopicBoardTypesClient topicBoardTypesClient = new TopicBoardTypesClient();
  private static TypesFactory typesFactory = new TypesFactory();
  private static Types type;
  private static String typeId;

  @BeforeAll
  public static void createTopicBoardAndAddType() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
    type = typesFactory.createTypes(DEFAULT);
    addTypesResponse = topicBoardTypesClient.addTypesToTopicBoard(topicBoardId, type);
    typeId = addTypesResponse.extract().path("id");
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить тип задачи из доски задач")
  public void deleteTypesInTopicBoardTest() {
    deleteTypesResponse = topicBoardTypesClient.deleteTypesInTopicBoard(topicBoardId, typeId);
    statusCode = extractStatusCode(deleteTypesResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
