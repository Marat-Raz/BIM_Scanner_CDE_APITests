package labels;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.restassured.response.ValidatableResponse;
import models.labels.ResponseLabel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetLabelsTests extends LabelBaseTests {

  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список меток проекта - код 200")
  public void getLabelFromProjectTest() {
    getResponse = labelsClient.getListOfLabelInProjectWithoutQueryOptions(projectId);
    statusCode = extractStatusCode(getResponse);
    ResponseLabel[] responseLabelsArray = getResponse.extract().as(ResponseLabel[].class);

    assertEquals(SC_OK, statusCode);
    assertTrue(responseLabelsArray.length > 0);
  }

}
