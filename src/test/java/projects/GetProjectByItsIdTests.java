package projects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import baseTests.StartTests;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
