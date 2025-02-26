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

public class UpdateTopicBoardByIdTests extends StartTests {

  private static ProjectsClient projectsClient = new ProjectsClient();
  private static ProjectFactory projectFactory = new ProjectFactory();
  private static TopicBoardsFactory topicBoardsFactory = new TopicBoardsFactory();
  private static TopicBoards topicBoard;
  private static String projectId;
  private static TopicBoardsClient topicBoardsClient = new TopicBoardsClient();
  private static ValidatableResponse createTopicBoardsResponse;
  private ValidatableResponse updateTopicBoardResponse;
  static String topicBoardId;

  @BeforeAll
  @Step("Создать проект, в ней создать группу досок задач")
  public static void createProject() {
    Project project = projectFactory.createProject(DEFAULT_PROJECT);
    project.setResponsibleId(userId);
    ValidatableResponse createProjectResponse = projectsClient.createProject(project);
    projectId = createProjectResponse.extract().path("id");
    topicBoard = topicBoardsFactory.createTopicBoards(DEFAULT_TOPIC_BOARDS);
    createTopicBoardsResponse = topicBoardsClient.createNewTopicBoard(projectId,
        topicBoard);
    topicBoardId = createTopicBoardsResponse.extract().path("id");
  }

  @AfterAll
  @Step("Удалить проект")
  public static void deleteProject() {
    projectsClient.deleteProjectByItsId(projectId);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить группу досок задач по его id")
  public void updateTopicBoardByIdTest() {
    TopicBoards newTopicBoard = topicBoard;
    newTopicBoard.setName("newName");
    updateTopicBoardResponse = topicBoardsClient
        .updateTopicBoard(projectId, topicBoardId, newTopicBoard);
    statusCode = extractStatusCode(updateTopicBoardResponse);
    ResponseTopicBoards responseTopicBoards = updateTopicBoardResponse.extract()
        .as(ResponseTopicBoards.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(newTopicBoard.getName(), responseTopicBoards.getName()),
        () -> assertEquals(topicBoardId, responseTopicBoards.getId()),
        () -> assertEquals(projectId, responseTopicBoards.projectId)
    );
  }

}
