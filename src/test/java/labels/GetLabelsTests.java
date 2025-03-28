package labels;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
@Story("Получение списка меток в проекте")
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
