package topicboards;

import static dtomodels.customfields.CustomFieldType.TEXT;
import static dtomodels.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;

import basetests.StartTests;
import client.CustomFieldsClient;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeCreateCustomFieldDto;
import dtomodels.customfields.CustomFieldFactory;
import dtomodels.topicboards.ResponseTopicBoards;
import dtomodels.topicboards.TopicBoards;
import dtomodels.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TopicBoardsBaseTests extends StartTests {

  protected static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  protected static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  protected static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  protected static ValidatableResponse addCustomFieldResponse;
  protected static ValidatableResponse editCustomFieldResponse;
  protected static CdeCreateCustomFieldDto cdeCreateCustomFieldDto;
  protected static String customFieldId;
  protected ValidatableResponse createTopicBoardsResponse;
  protected ResponseTopicBoards responseTopicBoard;
  protected TopicBoards topicBoard;
  protected String topicBoardId;

  @BeforeAll
  @Step("Добавить кастомные поля в проект")
  public static void addCustomFieldToProject() {
    cdeCreateCustomFieldDto = new CustomFieldFactory().createCustomField(TEXT);
    addCustomFieldResponse = customFieldsClient.addNewCustomFieldToProject(projectId,
        cdeCreateCustomFieldDto);
    customFieldId = addCustomFieldResponse.extract().path("id");
  }

  @BeforeEach
  @Step("Создать доску задач в проекте")
  public void createTopicBoard() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    responseTopicBoard = createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);
    topicBoardId = responseTopicBoard.getId();
  }

}
