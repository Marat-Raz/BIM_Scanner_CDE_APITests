package labels;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetLabelsTests extends LabelBaseTests {

  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список меток проекта - код 200")
  public void getLabelFromProjectTest() {

  }


}
