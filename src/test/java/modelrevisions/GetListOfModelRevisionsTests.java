package modelrevisions;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.RestAssuredLogging;
import dtomodels.PaginatedResponse;
import dtomodels.comment.Comment;
import dtomodels.models.modelrevisions.ResponseModelRevisions;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetListOfModelRevisionsTests extends ModelRevisionsBaseTests {

  private ValidatableResponse getListOfModelRevisionsResponse;
  private int modelRevisionCount = 3;
  private List<ResponseModelRevisions> modelRevisionsArrayFromResponse = new ArrayList<>();

  @BeforeEach
  public void setupMinimalLoggingAndUploadModelFiles() {
    RestAssuredLogging.setupMinimalLogging();
    for (int i = 0; i < modelRevisionCount; i++) {
      uploadModelFileResponse = modelRevisionsClient
          .uploadNewModelFile(projectId, modelId, modelFile, new Comment("comment №" + i));
      responseModelRevisions = uploadModelFileResponse.extract().as(ResponseModelRevisions.class);
      modelRevisionsArrayFromResponse.add(responseModelRevisions);
    }
  }

  @Test
  @Tag(value = "positive")
  @DisplayName("Получить список версий модели без фильтров - код ответа 200")
  public void uploadModelFileTest() {
    getListOfModelRevisionsResponse = modelRevisionsClient.getListOfModelRevisionsWithoutQueryOptions(
        projectId, modelId);
    statusCode = extractStatusCode(getListOfModelRevisionsResponse);
    PaginatedResponse<ResponseModelRevisions> paginatedResponse = getListOfModelRevisionsResponse
        .extract().as(PaginatedResponse.class);
    List<ResponseModelRevisions> arrayOfModelRevisions = paginatedResponse.getItems();

    assertEquals(SC_OK, statusCode);
    assertEquals(modelRevisionCount, paginatedResponse.getTotalCount());
  }
}
