package modelrevisions;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeModelRevisionDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ModelRevisions(Ревизии моделей)")
@Story("Получение ревизии модели по modelId и номеру версии")
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
    CdeModelRevisionDto modelRevisions = getModelRevisionResponse.extract()
        .as(CdeModelRevisionDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(modelId, modelRevisions.getModelId()),
        () -> assertEquals(modelVersion, modelRevisions.getVersion())
    );
  }

}
