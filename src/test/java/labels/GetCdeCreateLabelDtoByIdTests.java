package labels;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
@Story("Получение метки по id")
public class GetCdeCreateLabelDtoByIdTests extends LabelBaseTests {

  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить метку проекта по id - код 200")
  public void getLabelByIdTest() {
    getResponse = labelsClient.getLabelById(projectId, labelId);
    statusCode = extractStatusCode(getResponse);
    CdeLabelDto expectedLabel = getResponse.extract().as(CdeLabelDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(cdeLabelDtos.get(0).getName(), expectedLabel.getName()),
        () -> assertEquals(cdeLabelDtos.get(0).getColor(), expectedLabel.getColor()),
        () -> assertEquals(cdeLabelDtos.get(0).getId(), expectedLabel.getId())
    );
  }

}
