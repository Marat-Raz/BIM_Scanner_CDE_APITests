package projects;

import static client.base.Client.ADMIN_ACCESS_TOKEN;
import static dtomodels.project.ProjectType.RANDOM_PROJECT;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.base.Client;
import dtomodels.PaginatedResponse;
import dto.generated.CdeCreateProjectDto;
import dtomodels.project.ProjectFactory;
import dto.generated.CdeProjectDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Удаление проекта")
public class DeleteCdeCreateProjectDtoByItsIdTests extends StartTests {

  private ValidatableResponse getAllProjectResponse;
  private ValidatableResponse deleteProjectResponse;
  private static ArrayList<CdeCreateProjectDto> cdeCreateProjectDtoList = new ArrayList<CdeCreateProjectDto>();
  private static List<CdeProjectDto> cdeProjectDtoList = new ArrayList<>();
  private static int numberOfProjects = 3;

  @BeforeAll
  @Step("Создать проекты от имени ADMIN")
  public static void createProject() { // todo рассмотреть вынос этого метода в Steps
    for (int i = 0; i < numberOfProjects; i++) {
      cdeCreateProjectDtoList.add(new ProjectFactory().createProject(RANDOM_PROJECT));
    }
    for (CdeCreateProjectDto cdeCreateProjectDto : cdeCreateProjectDtoList) {
      projectsClient.createProject(ADMIN_ACCESS_TOKEN, cdeCreateProjectDto);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить все проекты пользователя ADMIN")
  public void deleteProjectByItsIdTest() {
    getAllProjectResponse = projectsClient.getListOfProjects(Client.ADMIN_ACCESS_TOKEN);
    PaginatedResponse<CdeProjectDto> projectPaginatedResponse =
        getAllProjectResponse.extract().body().as(typeRef);
    for (CdeProjectDto project : projectPaginatedResponse.getItems()) {
      deleteProjectResponse = projectsClient.deleteProjectByItsId(Client.ADMIN_ACCESS_TOKEN,
          project.getId());
      statusCode = extractStatusCode(deleteProjectResponse);

      assertEquals(SC_NO_CONTENT, statusCode);
    }
  }

}
