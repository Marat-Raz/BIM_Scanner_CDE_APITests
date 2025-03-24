package labels;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.labels.ResponseLabel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetLabelByIdTests extends LabelBaseTests {

  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить метку проекта по id - код 200")
  public void getLabelByIdTest() {
    getResponse = labelsClient.getLabelById(projectId, labelId);
    statusCode = extractStatusCode(getResponse);
    ResponseLabel expectedLabel = getResponse.extract().as(ResponseLabel.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(responseLabels.get(0).getName(), expectedLabel.getName()),
        () -> assertEquals(responseLabels.get(0).getColor(), expectedLabel.getColor()),
        () -> assertEquals(responseLabels.get(0).getId(), expectedLabel.getId())
    );
  }

}
