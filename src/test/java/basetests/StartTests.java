package basetests;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static dtomodels.project.ProjectType.DEFAULT_PROJECT;
import static dtomodels.user.UserType.DEFAULT_USER;

import client.ProjectsClient;
import client.TokenClient;
import client.UserClient;
import client.base.Client;
import dtomodels.PaginatedResponse;
import dtomodels.error.ErrorRoot;
import dtomodels.project.Project;
import dtomodels.project.ProjectFactory;
import dtomodels.project.ResponseProject;
import dtomodels.token.TokenBuilder;
import dtomodels.user.User;
import dtomodels.user.UserFactory;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class StartTests {

  private static TokenClient tokenClient = new TokenClient();
  protected static ProjectsClient projectsClient = new ProjectsClient();
  protected static ProjectFactory projectFactory = new ProjectFactory();
  protected static UserFactory userFactory = new UserFactory();
  protected static UserClient userClient = new UserClient();
  protected static TypeRef<PaginatedResponse<ResponseProject>> typeRef = new TypeRef<>() {
  };
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
    RestAssured.replaceFiltersWith(
        new RequestLoggingFilter(), new ResponseLoggingFilter(),
        new AllureRestAssured());
    ValidatableResponse responseAdminToken =
        tokenClient.createToken(TokenBuilder.getTokenForAdminUser());
    ADMIN_ACCESS_TOKEN = responseAdminToken.extract().path("access_token");

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
    PaginatedResponse<ResponseProject> projectPaginatedResponse =
        getAllProjectResponse.extract().body().as(typeRef);
    for (ResponseProject project : projectPaginatedResponse.getItems()) {
      projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());

    }
  }

  @Step("Получаем код ответа")
  public int extractStatusCode(ValidatableResponse response) {
    return response.extract().statusCode();
  }

}
/*
todo внедрить
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
 */