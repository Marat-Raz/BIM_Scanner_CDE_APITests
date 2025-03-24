package topiccomments;

import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static models.topiccomments.TopicCommentType.DEFAULT_TOPIC_COMMENT;
import static models.topics.TopicType.DEFAULT_TOPIC;

import basetests.StartTests;
import client.TopicBoardsClient;
import client.TopicCommentsClient;
import client.TopicsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import models.topiccomments.ResponseTopicComment;
import models.topiccomments.TopicComment;
import models.topiccomments.TopicCommentsFactory;
import models.topics.ResponseTopics;
import models.topics.Topics;
import models.topics.TopicsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicsCommentsBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static TopicsClient topicsClient = new TopicsClient();
  protected static TopicsFactory topicsFactory = new TopicsFactory();
  protected static TopicCommentsClient topicCommentsClient = new TopicCommentsClient();
  protected static TopicCommentsFactory topicCommentsFactory = new TopicCommentsFactory();
  protected static TopicBoards topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static ValidatableResponse addTopicsResponse;
  protected static ValidatableResponse addTopicsCommentsResponse;
  protected static Topics topic;
  protected static TopicComment topicComment;
  protected static ResponseTopics responseTopic;
  protected static ResponseTopicComment responseTopicComment;
  protected static String topicBoardId;
  protected static String defaultTopicId;
  protected static String topicCommentId;

  @BeforeAll
  @Step("Создаем доску задач в проекте и добавляем задачу")
  public static void createTopicBoardAndTopic() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();

    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicOnTopicBoard(topicBoardId, topic);
    responseTopic = addTopicsResponse.extract().as(ResponseTopics.class);
    defaultTopicId = responseTopic.getId();
  }

  @BeforeEach
  @Step("Добавляем комментарии к задаче")
  public void addTopicComment() {
    topicComment = topicCommentsFactory.createTopicComment(DEFAULT_TOPIC_COMMENT);
    addTopicsCommentsResponse = topicCommentsClient
        .createTopicComment(topicBoardId, defaultTopicId, topicComment);
    responseTopicComment = addTopicsCommentsResponse.extract().as(ResponseTopicComment.class);
    topicCommentId = responseTopicComment.getId();
  }

}
