package basetests;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static dtomodels.project.ProjectType.DEFAULT_PROJECT;
import static dtomodels.user.UserType.DEFAULT_USER;

import client.ProjectsClient;
import client.TokenClient;
import client.UserClient;
import client.base.Client;
import dto.generated.AbpIdentityUserCreateDto;
import dto.generated.AbpRemoteServiceErrorResponse;
import dto.generated.CdeCreateProjectDto;
import dto.generated.CdeProjectDto;
import dtomodels.PaginatedResponse;
import dtomodels.project.ProjectFactory;
import dtomodels.token.TokenBuilder;
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
  protected static TypeRef<PaginatedResponse<CdeProjectDto>> typeRef =
      new TypeRef<>() {
      };
  protected static AbpIdentityUserCreateDto defaultUser;
  protected static ValidatableResponse baseResponse;
  protected static ValidatableResponse createProjectResponse;
  protected static CdeCreateProjectDto defaultProject;
  protected static String projectId;
  protected static String userId;
  protected AbpRemoteServiceErrorResponse errorResponse;
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

    // todo выдать для abpIdentityUserCreateDto права на создание проектов раздел permission
  }

  @AfterAll
  @Step("Удаление профиля пользователя" +
      "Получить все проекты в системе и удалить все проекты всех пользователей после тестов")
  public static void cleanData() {
    userClient.deleteUser(userId);
    ValidatableResponse getAllProjectResponse =
        projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    PaginatedResponse<CdeProjectDto> projectPaginatedResponse =
        getAllProjectResponse.extract().body().as(typeRef);
    for (CdeProjectDto project : projectPaginatedResponse.getItems()) {
      projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());
    }
    // todo удалить всех пользователей
  }

  @Step("Получаем код ответа")
  public int extractStatusCode(ValidatableResponse response) {
    return response.extract().statusCode();
  }

}
