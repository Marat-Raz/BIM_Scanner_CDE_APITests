package projects;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteProjectCoverImageTests extends StartTests {

  private ValidatableResponse deleteIconResponse;

  @BeforeEach
  @Step("Установить обложку проекта")
  public void setProjectCoverImage() {
    projectsClient.setProjectCoverImage(projectId);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить изображение обложки проекта")
  public void getProjectCoverImageTest() {
    deleteIconResponse = projectsClient.deleteProjectCoverImage(projectId);
    statusCode = extractStatusCode(deleteIconResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }
}

