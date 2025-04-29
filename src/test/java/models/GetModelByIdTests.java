package models;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dtomodels.models.ResponseModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Models(Модели)")
@Story("Получение модели по id")
public class GetModelByIdTests extends ModelsBaseTests {

  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на получение модели по id дает ответ 200")
  public void getModelByIdTest() {
    getResponse = modelsClient.getModelById(projectId, defaultModelId);
    statusCode = extractStatusCode(getResponse);
    ResponseModel testModel = getResponse.extract().as(ResponseModel.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(defaultResponseModel.getName(), testModel.getName()),
        () -> assertNotNull(testModel.getId())
    );
  }


}
