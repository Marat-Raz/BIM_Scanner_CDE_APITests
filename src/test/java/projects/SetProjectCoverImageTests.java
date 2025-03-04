package projects;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SetProjectCoverImageTests extends StartTests {
// todo https://software-testing.ru/library/testing/testing-for-beginners/3318-six-tips-and-four-tools-for-file-upload

  private ValidatableResponse setIconResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Задать изображение обложки проекта")
  public void setProjectCoverImageTest() {
    setIconResponse = projectsClient.setProjectCoverImage(projectId);
    statusCode = extractStatusCode(setIconResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
