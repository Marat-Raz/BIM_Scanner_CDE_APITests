package basetests;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static dtomodels.project.ProjectType.DEFAULT_PROJECT;
import static dtomodels.user.UserType.DEFAULT_USER;

import client.ProjectsClient;
import client.TokenClient;
import client.UserClient;
import client.base.Client;
import dtomodels.PaginatedResponse;
import dto.generated.AbpRemoteServiceErrorResponse;
import dto.generated.CdeCreateProjectDto;
import dtomodels.project.ProjectFactory;
import dto.generated.CdeProjectDto;
import dtomodels.token.TokenBuilder;
import dto.generated.AbpIdentityUserCreateDto;
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
  protected static TypeRef<PaginatedResponse<CdeProjectDto>> typeRef = new TypeRef<>() {
  };
  protected static AbpIdentityUserCreateDto defaultAbpIdentityUserCreateDto;
  protected static ValidatableResponse baseResponse;
  protected static ValidatableResponse createProjectResponse;
  protected static CdeCreateProjectDto defaultCdeCreateProjectDto;
  protected static String projectId;
  protected static String userId;
  protected AbpRemoteServiceErrorResponse abpRemoteServiceErrorResponse;
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

    defaultAbpIdentityUserCreateDto = userFactory.createUser(DEFAULT_USER);
    baseResponse = userClient.createUser(defaultAbpIdentityUserCreateDto);
    userId = baseResponse.extract().path("id");

    defaultCdeCreateProjectDto = projectFactory.createProject(DEFAULT_PROJECT);
    createProjectResponse = projectsClient.createProject(defaultCdeCreateProjectDto);
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
    PaginatedResponse<CdeProjectDto> projectPaginatedResponse =
        getAllProjectResponse.extract().body().as(typeRef);
    for (CdeProjectDto project : projectPaginatedResponse.getItems()) {
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