package topicboardtypes;

import static models.types.TypesType.DEFAULT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.TopicBoardTypesClient;
import client.TopicBoardsClient;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import models.types.Types;
import models.types.TypesFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardTypesTests extends StartTests {

  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static ValidatableResponse createTopicBoardsResponse;
  private static String topicBoardId;
  private static ValidatableResponse addTypesResponse;
  private static TopicBoardTypesClient topicBoardTypesClient = new TopicBoardTypesClient();
  private static final int typesCount = 5;
  private ValidatableResponse getAllTypes;
  private static List<Types> expectedTypes = new ArrayList<>();

  @BeforeAll
  public static void createTopicBoardAndAddType() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();

    for (int i = 0; i < typesCount; i++) {
      expectedTypes.add(new TypesFactory().createTypes(DEFAULT));
    }
    for (Types status : expectedTypes) {
      addTypesResponse = topicBoardTypesClient.addTypesToTopicBoard(topicBoardId, status);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все приоритеты доски задач")
  public void getTopicBoardTypesTest() {
    getAllTypes = topicBoardTypesClient.getTopicBoardTypes(topicBoardId);
    List<Types> actualTypes = Arrays.asList(getAllTypes.extract().as(Types[].class));

    assertAll(
        () -> assertEquals(typesCount, actualTypes.size()),
        () -> assertEquals(new HashSet<>(expectedTypes), new HashSet<>(actualTypes))
    );
  }
}
