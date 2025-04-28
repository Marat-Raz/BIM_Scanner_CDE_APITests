package labels;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeCreateLabelDto;
import dtomodels.labels.LabelFactory;
import dto.generated.CdeLabelDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Labels(Метки)")
@Story("Редактирование метки")
public class EditLabelTests extends LabelBaseTests {

  private ValidatableResponse putResponse;
  private ValidatableResponse getResponse;
  private LabelFactory labelFactory = new LabelFactory();

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить метку проекта по id - код 200")
  public void editLabelByIdTest() {
    getResponse = labelsClient.getListOfLabelInProjectWithoutQueryOptions(projectId);
    CdeLabelDto[] cdeLabelsArrayDto = getResponse.extract().as(CdeLabelDto[].class);
    labelId = cdeLabelsArrayDto[0].getId();

    CdeCreateLabelDto newLabel = labelFactory.from(cdeLabelsArrayDto[0]);
    newLabel.setName("It's new name");

    putResponse = labelsClient.updateLabelInProject(projectId, labelId, newLabel);
    statusCode = extractStatusCode(putResponse);
    CdeLabelDto actualLabel = putResponse.extract().as(CdeLabelDto.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(newLabel.getName(), actualLabel.getName());
  }

}
