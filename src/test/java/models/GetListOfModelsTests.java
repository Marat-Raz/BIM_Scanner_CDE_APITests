package models;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtomodels.models.ListOfModel;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetListOfModelsTests extends ModelsBaseTests {

  private ValidatableResponse getModelsResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на получение списка моделей дает ответ 200")
  public void getModelsTest() {
    getModelsResponse = modelsClient.getListOfModelsInProjectWithoutQueryOptions(projectId);
    statusCode = extractStatusCode(getModelsResponse);
    listOfModel = getModelsResponse.extract().as(ListOfModel.class);

    assertEquals(SC_OK, statusCode);
    assertTrue(listOfModel.getTotalCount() > 0);
  }
}
