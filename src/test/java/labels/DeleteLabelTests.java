package labels;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import dtomodels.labels.ResponseLabel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeleteLabelTests extends LabelBaseTests {

  private ValidatableResponse deleteResponse;
  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить метку проекта по id - код 200")
  public void deleteLabelByIdTest() {
    getResponse = labelsClient.getListOfLabelInProjectWithoutQueryOptions(projectId);
    ResponseLabel[] responseLabelsArray = getResponse.extract().as(ResponseLabel[].class);
    labelId = responseLabelsArray[0].getId();
    deleteResponse = labelsClient.deleteLabelInProject(projectId, labelId);
    statusCode = extractStatusCode(deleteResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
