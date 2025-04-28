package modelrevisions;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dto.generated.CdeModelConversionStatusDto;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetStatusOfModelConversionTests extends ModelRevisionsBaseTests {

  private ValidatableResponse getStatusOfModelConversionResponse;
  private CdeModelConversionStatusDto cdeModelConversionStatusDto;

  @Test
  @Tag(value = "positive")
  @DisplayName("Получить статус преобразования модели - код ответа 200")
  public void updateModelRevisionTest() {
    int modelVersion = 2;
    getStatusOfModelConversionResponse = modelRevisionsClient
        .getModelConversionStatus(projectId, modelId, modelVersion);
    statusCode = extractStatusCode(getStatusOfModelConversionResponse);
    cdeModelConversionStatusDto = getStatusOfModelConversionResponse.extract()
        .as(CdeModelConversionStatusDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(modelVersion, cdeModelConversionStatusDto.getVersion()),
        () -> assertNotNull(cdeModelConversionStatusDto.getId())
    );
  }

}
