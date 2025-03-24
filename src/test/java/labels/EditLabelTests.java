package labels;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.labels.Label;
import models.labels.LabelFactory;
import models.labels.ResponseLabel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class EditLabelTests extends LabelBaseTests {

  private ValidatableResponse putResponse;
  private ValidatableResponse getResponse;
  private LabelFactory labelFactory = new LabelFactory();

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить метку проекта по id - код 200")
  public void editLabelByIdTest() {
    getResponse = labelsClient.getListOfLabelInProjectWithoutQueryOptions(projectId);
    ResponseLabel[] responseLabelsArray = getResponse.extract().as(ResponseLabel[].class);
    labelId = responseLabelsArray[0].getId();

    Label newLabel = labelFactory.from(responseLabelsArray[0]);
    newLabel.setName("It's new name");

    putResponse = labelsClient.updateLabelInProject(projectId, labelId, newLabel);
    statusCode = extractStatusCode(putResponse);
    ResponseLabel actualLabel = putResponse.extract().as(ResponseLabel.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(newLabel.getName(), actualLabel.getName());
  }

}
