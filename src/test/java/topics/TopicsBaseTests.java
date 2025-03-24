package topics;

import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static dtomodels.topics.TopicType.DEFAULT_TOPIC;

import basetests.StartTests;
import client.TopicBoardsClient;
import client.TopicsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dtomodels.topicboards.ResponseTopicBoards;
import dtomodels.topicboards.TopicBoards;
import dtomodels.topicboards.TopicBoardsFactory;
import dtomodels.topics.ResponseTopics;
import dtomodels.topics.Topics;
import dtomodels.topics.TopicsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicsBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static TopicBoards topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static TopicsClient topicsClient = new TopicsClient();
  protected static TopicsFactory topicsFactory = new TopicsFactory();
  protected static String topicBoardId;
  protected ValidatableResponse addTopicsResponse;
  protected Topics topic;
  protected String defaultTopicId;
  protected ResponseTopics responseTopic;

  @BeforeAll
  @Step("Создаем доску задач в проекте")
  public static void createTopicBoardAndTopic() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
  }

  @BeforeEach
  @Step("Добавляем задачу в доску задач")
  public void addTopic() {
    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicOnTopicBoard(topicBoardId, topic);
    responseTopic = addTopicsResponse.extract().as(ResponseTopics.class);
    defaultTopicId = responseTopic.getId();
  }

}
