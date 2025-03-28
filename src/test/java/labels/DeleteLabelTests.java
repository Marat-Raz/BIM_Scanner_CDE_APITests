package labels;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.labels.ResponseLabel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Labels(Метки)")
@Story("Удаление метки из проекта")
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
