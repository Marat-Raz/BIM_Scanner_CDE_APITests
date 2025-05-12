package topiccomments;

import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static dtomodels.comment.CommentType.DEFAULT_TOPIC_COMMENT;
import static dtomodels.topics.TopicType.DEFAULT_TOPIC;

import basetests.StartTests;
import client.TopicBoardsClient;
import client.TopicCommentsClient;
import client.TopicsClient;
import dto.generated.CdeCreateTopicCommentDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeCreateTopicBoardDto;
import dtomodels.topicboards.TopicBoardsFactory;
import dto.generated.CdeTopicCommentDto;
import dtomodels.comment.CommentsFactory;
import dto.generated.CdeTopicDetailsDto;
import dto.generated.CdeCreateTopicDto;
import dtomodels.topics.TopicsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class CreateTopicCommentsBaseTests extends StartTests {

  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static TopicsClient topicsClient = new TopicsClient();
  protected static TopicsFactory topicsFactory = new TopicsFactory();
  protected static TopicCommentsClient topicCommentsClient = new TopicCommentsClient();
  protected static CommentsFactory commentsFactory = new CommentsFactory();
  protected static CdeCreateTopicBoardDto topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static ValidatableResponse addTopicsResponse;
  protected static ValidatableResponse addTopicsCommentsResponse;
  protected static CdeCreateTopicDto topic;
  protected static CdeCreateTopicCommentDto createTopicComment;
  protected static CdeTopicDetailsDto responseTopic;
  protected static CdeTopicCommentDto cdeTopicCommentDto;
  protected static String topicBoardId;
  protected static String defaultTopicId;
  protected static String topicCommentId;

  @BeforeAll
  @Step("Создаем доску задач в проекте и добавляем задачу")
  public static void createTopicBoardAndTopic() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    CdeTopicBoardDto cdeTopicBoardDto =
        createTopicBoardsResponse.extract().as(CdeTopicBoardDto.class);
    topicBoardId = cdeTopicBoardDto.getId();

    topic = topicsFactory.createTopic(DEFAULT_TOPIC);
    addTopicsResponse = topicsClient.createTopicOnTopicBoard(topicBoardId, topic);
    responseTopic = addTopicsResponse.extract().as(CdeTopicDetailsDto.class);
    defaultTopicId = responseTopic.getId();
  }

  @BeforeEach
  @Step("Добавляем комментарии к задаче")
  public void addTopicComment() {
    createTopicComment = commentsFactory.createTopicComment(DEFAULT_TOPIC_COMMENT);
    addTopicsCommentsResponse = topicCommentsClient
        .createTopicComment(topicBoardId, defaultTopicId, createTopicComment);
    cdeTopicCommentDto = addTopicsCommentsResponse.extract().as(CdeTopicCommentDto.class);
    topicCommentId = cdeTopicCommentDto.getId();
  }

}
