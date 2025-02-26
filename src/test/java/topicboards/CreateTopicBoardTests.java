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

public class CreateTopicBoardTests extends StartTests {

  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static String projectId;
  private TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private TopicBoards topicBoard;
  private ValidatableResponse createTopicBoardsResponse;

  @BeforeAll
  @Step("Создать проект")
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
  }

  @AfterAll
  public static void deleteProject() {
    projectsClient.deleteProjectByItsId(projectId);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать доску задач в корне проекта")
  public void createTopicBoardsGroupTest() {
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId, topicBoard);
    statusCode = extractStatusCode(createTopicBoardsResponse);
    ResponseTopicBoards responseTopicBoards =
        createTopicBoardsResponse.extract().as(ResponseTopicBoards.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals("TopicBoard", responseTopicBoards.type),
        () -> assertEquals(topicBoard.getName(), responseTopicBoards.name),
        () -> assertEquals(projectId, responseTopicBoards.projectId)
    );
  }

  // todo реализовать тесты из TestIT
}
