package models;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtomodels.PaginatedResponse;
import dto.generated.CdeModelDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Models(Модели)")
@Story("Получение списка моделей")
public class GetListOfModelsTests extends ModelsBaseTests {

  private ValidatableResponse getModelsResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на получение списка моделей дает ответ 200")
  public void getModelsTest() {
    getModelsResponse = modelsClient.getListOfModelsInProjectWithoutQueryOptions(projectId);
    statusCode = extractStatusCode(getModelsResponse);
    PaginatedResponse<CdeModelDto> paginatedResponse = getModelsResponse
        .extract().as(PaginatedResponse.class);
    ArrayList<CdeModelDto> arrayOfModel = paginatedResponse.getItems();

    assertEquals(SC_OK, statusCode);
    assertTrue(arrayOfModel.size() > 0);
  }
}
