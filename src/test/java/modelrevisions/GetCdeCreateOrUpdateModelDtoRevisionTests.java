package modelrevisions;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.models.modelrevisions.ResponseModelRevisions;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetModelRevisionTests extends ModelRevisionsBaseTests {

  private ValidatableResponse getModelRevisionResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Получить ревизию модели по modelId и номеру версии - код ответа 200")
  public void getModelRevisionTest() {
    int modelVersion = 3;
    getModelRevisionResponse = modelRevisionsClient
        .getModelRevisionByVersion(projectId, modelId, modelVersion);
    statusCode = extractStatusCode(getModelRevisionResponse);
    ResponseModelRevisions modelRevisions = getModelRevisionResponse.extract()
        .as(ResponseModelRevisions.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(modelId, modelRevisions.getModelId()),
        () -> assertEquals(modelVersion, modelRevisions.getVersion())
    );
  }

}
