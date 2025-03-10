package topicboards;

import static models.customfields.CustomFieldType.TEXT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;

import basetests.StartTests;
import client.CustomFieldsClient;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.customfields.CustomField;
import models.customfields.CustomFieldFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicBoardsBaseTests extends StartTests {

  protected static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static ValidatableResponse addCustomFieldResponse;
  protected static ValidatableResponse editCustomFieldResponse;
  protected static CustomField customField;
  protected static String customFieldId;
  protected ValidatableResponse createTopicBoardsResponse;
  protected ResponseTopicBoards responseTopicBoard;
  protected TopicBoards topicBoard;
  protected String topicBoardId;

  @BeforeAll
  @Step("Добавить кастомные поля в проект, создать доску задач в проекте")
  public static void addCustomFieldToProject() {
    customField = new CustomFieldFactory().createCustomField(TEXT);
    addCustomFieldResponse = customFieldsClient.addNewCustomFieldToProject(projectId, customField);
    customFieldId = addCustomFieldResponse.extract().path("id");
  }

  @BeforeEach
  public void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    responseTopicBoard = createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoard.getId();
  }

}
