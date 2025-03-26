package modelrevisions;

import basetests.RestAssuredLogging;
import dtomodels.models.modelrevisions.ResponseModelRevisions;
import org.junit.jupiter.api.*;

public class GetListOfModelRevisionsTests extends ModelRevisionsBaseTests {

  @BeforeEach
  public void setupMinimalLoggingAndUploadModelFiles() {
    RestAssuredLogging.setupMinimalLogging();
    for (int i = 0; i < 3; i++) {
      uploadModelFileResponse = modelRevisionsClient
          .uploadNewModelFile(projectId, modelId, modelFile, "comment №" + i);
      responseModelRevisions = uploadModelFileResponse.extract()
          .as(ResponseModelRevisions.class);
    }
  }

  @Test
  @Tag(value = "positive")
  @DisplayName("Загрузить новый файл модели - код ответа 200")
  public void uploadModelFileTest() {
    modelRevisionsClient.getListOfModelRevisions()
  }
}
