package topicevents;

import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static dtomodels.topics.TopicType.DEFAULT_TOPIC;

import basetests.StartTests;
import client.TopicBoardsClient;
import client.TopicEventsClient;
import client.TopicsClient;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import dtomodels.topicboards.ResponseTopicBoards;
import dtomodels.topicboards.TopicBoards;
import dtomodels.topicboards.TopicBoardsFactory;
import dtomodels.topics.ResponseTopics;
import dtomodels.topics.Topics;
import dtomodels.topics.TopicsFactory;
import org.junit.jupiter.api.BeforeAll;

public class TopicEventsBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected TopicEventsClient topicEventsClient = new TopicEventsClient();
  protected static TopicBoards topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static ValidatableResponse addTopicsResponse;
  protected static TopicsClient topicsClient = new TopicsClient();
  protected static String defaultTopicId;
  protected static ResponseTopics responseTopic;
  protected static String topicBoardId;

  @BeforeAll
  public static void createTopicBoardAndTopic() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();

    ArrayList<Topics> topicList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      topicList.add(new TopicsFactory().createTopic(DEFAULT_TOPIC));
    }
    ArrayList<ResponseTopics> responseTopicsArray = new ArrayList<>();
    for (Topics topic : topicList) {
      addTopicsResponse = topicsClient.createTopicOnTopicBoard(topicBoardId, topic);
      responseTopic = addTopicsResponse.extract().as(ResponseTopics.class);
      responseTopicsArray.add(responseTopic);
    }
    defaultTopicId = responseTopicsArray.get(0).getId();

  }

}
