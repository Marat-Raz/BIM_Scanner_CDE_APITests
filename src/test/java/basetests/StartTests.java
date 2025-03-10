package basetests;

import static models.project.ProjectType.DEFAULT_PROJECT;
import static models.user.UserType.DEFAULT_USER;

import client.ProjectsClient;
import client.TokenClient;
import client.UserClient;
import client.base.Client;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import models.error.ErrorRoot;
import models.project.Project;
import models.project.ProjectFactory;
import models.project.ServerResponseProject;
import models.token.TokenBuilder;
import models.user.User;
import models.user.UserFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class StartTests {

  private static TokenClient tokenClient = new TokenClient();
  protected static ProjectsClient projectsClient = new ProjectsClient();
  protected static ProjectFactory projectFactory = new ProjectFactory();
  protected static UserFactory userFactory = new UserFactory();
  protected static UserClient userClient = new UserClient();
  protected static User defaultUser;
  protected static ValidatableResponse baseResponse;
  protected static ValidatableResponse createProjectResponse;
  protected static Project defaultProject;
  protected static String projectId;
  protected static String userId;
  protected ErrorRoot errorRoot;
  protected int statusCode;

  @BeforeAll
  @Step("Запуск Allure и логирования запросов по API, \n"
      + "получение токена пользователя admin для запросов, требующих прав админа, \n"
      + "создание пользователя по умолчанию.\n")
  public static void globalSetUp() {
    RestAssured.filters(
        new RequestLoggingFilter(), new ResponseLoggingFilter(),
        new AllureRestAssured());
    ValidatableResponse responseAdminToken =
        tokenClient.createToken(TokenBuilder.getTokenForAdminUser());
    Client.ADMIN_ACCESS_TOKEN = responseAdminToken.extract().path("access_token");
    //String adminId = userClient.getUserByUserName("admin").extract().path("id");

    defaultUser = userFactory.createUser(DEFAULT_USER);
    baseResponse = userClient.createUser(defaultUser);
    userId = baseResponse.extract().path("id");

    defaultProject = projectFactory.createProject(DEFAULT_PROJECT);
    createProjectResponse = projectsClient.createProject(defaultProject);
    projectId = createProjectResponse.extract().path("id");

    // todo выдать для user права на создание проектов раздел permission
  }

  @AfterAll
  @Step("Удаление профиля пользователя" +
      "Получить все проекты в системе и удалить все проекты всех пользователей после тестов")
  public static void cleanData() {
    userClient.deleteUser(userId);
    ValidatableResponse getAllProjectResponse =
        projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    List<ServerResponseProject> serverResponseProjectList =
        List.of(getAllProjectResponse.extract().body().as(ServerResponseProject[].class));
    for (ServerResponseProject project : serverResponseProjectList) {
      projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());
    }
  }

  @Step("Получаем код ответа")
  public int extractStatusCode(ValidatableResponse response) {
    return response.extract().statusCode();
  }

}