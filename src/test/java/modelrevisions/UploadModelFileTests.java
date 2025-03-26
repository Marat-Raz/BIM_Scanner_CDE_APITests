package modelrevisions;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.RestAssuredLogging;
import dtomodels.modelrevisions.ResponseModelRevisions;
import org.junit.jupiter.api.*;

public class UploadModelFileTests extends ModelRevisionsBaseTests {

  @BeforeEach
  public void setupMinimalLoggingAndUploadModelFile() {
    RestAssuredLogging.setupMinimalLogging();
    uploadModelFileResponse = modelRevisionsClient
        .uploadNewModelFile(projectId, modelId, modelFile, "comment");
    responseModelRevisions = uploadModelFileResponse.extract()
        .as(ResponseModelRevisions.class);
  }

  @AfterEach
  void restoreOriginalFilters() {
    RestAssuredLogging.restoreOriginalFilters();
  }

  @Test
  @Tag(value = "positive")
  @DisplayName("Загрузить новый файл модели - код ответа 200")
  public void uploadModelFileTest() {
    statusCode = extractStatusCode(uploadModelFileResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(modelId, responseModelRevisions.getModelId())
    );
  }
}
