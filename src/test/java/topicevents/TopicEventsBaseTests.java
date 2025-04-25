package topicevents;

import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static dtomodels.topics.TopicType.DEFAULT_TOPIC;

import basetests.StartTests;
import client.TopicBoardsClient;
import client.TopicEventsClient;
import client.TopicsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeCreateTopicBoardDto;
import dtomodels.topicboards.TopicBoardsFactory;
import dto.generated.CdeTopicDetailsDto;
import dto.generated.CdeCreateTopicDto;
import dtomodels.topics.TopicsFactory;
import org.junit.jupiter.api.BeforeAll;

public class TopicEventsBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected TopicEventsClient topicEventsClient = new TopicEventsClient();
  protected static CdeCreateTopicBoardDto topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static ValidatableResponse addTopicsResponse;
  protected static TopicsClient topicsClient = new TopicsClient();
  protected static String defaultTopicId;
  protected static CdeTopicDetailsDto responseTopic;
  protected static String topicBoardId;

  @BeforeAll
  @Step("Создаем доску задач в проекте и добавляем несколько задач")
  public static void createTopicBoardAndTopic() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    CdeTopicBoardDto cdeTopicBoardDto =
        createTopicBoardsResponse.extract().as(CdeTopicBoardDto.class);
    topicBoardId = cdeTopicBoardDto.getId();

    ArrayList<CdeCreateTopicDto> topicList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      topicList.add(new TopicsFactory().createTopic(DEFAULT_TOPIC));
    }
    ArrayList<CdeTopicDetailsDto> cdeTopicDetailsDtoArray = new ArrayList<>();
    for (CdeCreateTopicDto topic : topicList) {
      addTopicsResponse = topicsClient.createTopicOnTopicBoard(topicBoardId, topic);
      responseTopic = addTopicsResponse.extract().as(CdeTopicDetailsDto.class);
      cdeTopicDetailsDtoArray.add(responseTopic);
    }
    defaultTopicId = cdeTopicDetailsDtoArray.get(0).getId();
  }

}
