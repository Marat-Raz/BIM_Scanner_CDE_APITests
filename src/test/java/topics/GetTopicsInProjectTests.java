package topics;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static models.topics.TopicType.DEFAULT_TOPIC;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import models.topics.ResponseFromGetAllTopics;
import models.topics.ResponseTopics;
import models.topics.Topics;
import models.topics.TopicsFactory;
import org.junit.jupiter.api.*;

public class GetTopicsInProjectTests extends TopicsBaseTests {

  private static Topics newTopic;
  private static ValidatableResponse createTopicBoardsResponse;
  private ValidatableResponse getListOfTopicsFromProjectResponse;

  @BeforeEach
  public void createNewTopicBoardAndNewTopic() {
    TopicBoards newTopicBoard = new TopicBoardsFactory().createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, newTopicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    newTopic = new TopicsFactory().createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicOnTopicBoard(responseTopicBoards.getId(), newTopic);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все задачи из проекта")
  public void getTopicsInProjectTest() {
    getListOfTopicsFromProjectResponse = topicsClient
        .getTopicsInProjectWithoutQueryOptions(projectId);
    statusCode = extractStatusCode(getListOfTopicsFromProjectResponse);
    ResponseFromGetAllTopics responseFromGetAllTopics = getListOfTopicsFromProjectResponse
        .extract().as(ResponseFromGetAllTopics.class);
    ArrayList<ResponseTopics> arrayOfTopics = responseFromGetAllTopics.getItems();

    assertEquals(SC_OK, statusCode);
    assertEquals(responseFromGetAllTopics.getTotalCount(),
        arrayOfTopics.size()); // fixme нужно другим образом сравнить
  }
}
