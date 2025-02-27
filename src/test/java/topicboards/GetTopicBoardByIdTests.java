package topicboards;

import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.topicboards.TopicBoardsType.DEFAULT_TOPIC_BOARDS;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import client.ProjectsClient;
import client.TopicBoardsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.project.Project;
import models.project.ProjectFactory;
import models.topicboards.ResponseTopicBoards;
import models.topicboards.TopicBoards;
import models.topicboards.TopicBoardsFactory;
import org.junit.jupiter.api.*;

public class GetTopicBoardByIdTests extends StartTests {

  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static ResponseTopicBoards responseTopicBoard;
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static String projectId;
  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static ValidatableResponse createTopicBoardsResponse;
  private ValidatableResponse getTopicBoardResponse;
  private static String topicBoardId;

  @BeforeAll
  @Step("Создать проект, в ней создать доску задач")
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    topicBoardId = createTopicBoardsResponse.extract().path("id");
  }

  @AfterAll
  @Step("Удалить проект")
  public static void deleteProject() {
    projectsClient.deleteProjectByItsId(projectId);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить доску задач по его id")
  public void getTopicBoardByIdTest() {
    getTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    statusCode = extractStatusCode(getTopicBoardResponse);
    responseTopicBoard = getTopicBoardResponse.extract()
        .as(ResponseTopicBoards.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals("TopicBoard", responseTopicBoard.type),
        () -> assertEquals(topicBoard.getName(), responseTopicBoard.name),
        () -> assertEquals(projectId, responseTopicBoard.projectId)
    );
  }

}
