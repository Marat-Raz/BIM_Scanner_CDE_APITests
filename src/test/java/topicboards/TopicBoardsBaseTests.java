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

public class TopicBoardsBaseTests extends StartTests {

  protected static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static ValidatableResponse addCustomFieldResponse;
  protected static ValidatableResponse editCustomFieldResponse;
  protected static CustomField customField;
  protected static String customFieldId;
  protected static TopicBoards topicBoard;
  protected static ValidatableResponse createTopicBoardsResponse;
  protected static String topicBoardId;
  protected static ResponseTopicBoards responseTopicBoards;

  @BeforeAll
  @Step("Добавить кастомные поля в проект")
  public static void addCustomFieldToProjectAndCreateTopicBoard() {
    customField = new CustomFieldFactory().createCustomField(TEXT);
    addCustomFieldResponse = customFieldsClient.addNewCustomFieldToProject(projectId, customField);
    customFieldId = addCustomFieldResponse.extract().path("id");

    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    responseTopicBoards = createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoards.getId();
  }
}
