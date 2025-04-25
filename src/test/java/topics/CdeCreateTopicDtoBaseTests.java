package topics;

import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static dtomodels.topics.TopicType.DEFAULT_TOPIC;

import basetests.StartTests;
import client.TopicBoardsClient;
import client.TopicsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeCreateTopicBoardDto;
import dtomodels.topicboards.TopicBoardsFactory;
import dtomodels.topics.CdeCreateTopicDto;
import dtomodels.topics.Topics;
import dtomodels.topics.TopicsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicsBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static CdeCreateTopicBoardDto topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static TopicsClient topicsClient = new TopicsClient();
  protected static TopicsFactory topicsFactory = new TopicsFactory();
  protected static String topicBoardId;
  protected ValidatableResponse addTopicsResponse;
  protected Topics topic;
  protected String defaultTopicId;
  protected CdeCreateTopicDto responseTopic;

  @BeforeAll
  @Step("Создаем доску задач в проекте")
  public static void createTopicBoardAndTopic() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    CdeTopicBoardDto cdeTopicBoardDto =
        createTopicBoardsResponse.extract().as(CdeTopicBoardDto.class);
    topicBoardId = cdeTopicBoardDto.getId();
  }

  @BeforeEach
  @Step("Добавляем задачу в доску задач")
  public void addTopic() {
    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicOnTopicBoard(topicBoardId, topic);
    responseTopic = addTopicsResponse.extract().as(CdeCreateTopicDto.class);
    defaultTopicId = responseTopic.getId();
  }

}
