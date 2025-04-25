package projects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Получение проекта по id")
public class GetProjectByItsIdTests extends StartTests {

  private ValidatableResponse getProjectResponse;
  private String requestProjectId;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить проект по id")
  public void getProjectByIdTest() {
    getProjectResponse = projectsClient.getProjectByItsIdForAdmin(projectId);
    requestProjectId = getProjectResponse.extract().path("id");

    assertEquals(requestProjectId, requestProjectId);
  }

}
