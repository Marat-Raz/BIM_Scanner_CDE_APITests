package modelrevisions;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtomodels.PaginatedResponse;
import dto.generated.CdeModelRevisionDto;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetListOfModelRevisionsTests extends ModelRevisionsBaseTests {

  private ValidatableResponse getListOfModelRevisionsResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Получить список версий модели без фильтров - код ответа 200")
  public void uploadModelFileTest() {
    getListOfModelRevisionsResponse = modelRevisionsClient.getListOfModelRevisionsWithoutQueryOptions(
        projectId, modelId);
    statusCode = extractStatusCode(getListOfModelRevisionsResponse);
    PaginatedResponse<CdeModelRevisionDto> paginatedResponse = getListOfModelRevisionsResponse
        .extract().as(PaginatedResponse.class);
    List<CdeModelRevisionDto> arrayOfModelRevisions = paginatedResponse.getItems();

    assertEquals(SC_OK, statusCode);
    assertEquals(modelRevisionCount, paginatedResponse.getTotalCount());
  }
}
