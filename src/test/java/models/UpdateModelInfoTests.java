package models;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.models.Model;
import dtomodels.models.ResponseModel;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdateModelInfoTests extends ModelsBaseTests {

  private ValidatableResponse updateResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на обновление информации модели по id дает ответ 200")
  public void updateModelInfoTest() {
    Model newModel = defaultModel;
    newModel.setName("New name");
    updateResponse = modelsClient.updateModelById(projectId, defaultModelId, newModel);
    statusCode = extractStatusCode(updateResponse);
    ResponseModel testModel = updateResponse.extract().as(ResponseModel.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(newModel.getName(), testModel.getName()),
        () -> assertEquals(defaultModelId, testModel.getId())
    );
  }
}
