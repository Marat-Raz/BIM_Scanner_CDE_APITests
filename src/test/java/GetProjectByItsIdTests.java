import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetProjectByItsIdTests extends StartTests {
  ValidatableResponse getProjectResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить проект по id")
  public void getUserByIdTest() {
    // todo сначала нужно создать проект

  }

}
