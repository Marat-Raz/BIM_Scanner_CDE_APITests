package models;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeCreateOrUpdateModelDto;
import dto.generated.CdeModelDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Models(Модели)")
@Story("Редактирование модели в проекте")
public class UpdateCdeCreateOrUpdateModelDtoInfoTests extends ModelsBaseTests {

  private ValidatableResponse updateResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на обновление информации модели по id дает ответ 200")
  public void updateModelInfoTest() {
    CdeCreateOrUpdateModelDto newCdeCreateOrUpdateModelDto = defaultCdeCreateOrUpdateModelDto;
    newCdeCreateOrUpdateModelDto.setName("New name");
    updateResponse = modelsClient.updateModelById(projectId, defaultModelId,
        newCdeCreateOrUpdateModelDto);
    statusCode = extractStatusCode(updateResponse);
    CdeModelDto testModel = updateResponse.extract().as(CdeModelDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(newCdeCreateOrUpdateModelDto.getName(), testModel.getName()),
        () -> assertEquals(defaultModelId, testModel.getId())
    );
  }
}
