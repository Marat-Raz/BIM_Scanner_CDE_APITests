package modelrevisions;

import static dtomodels.models.ModelFileFormat.IFC;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import basetests.RestAssuredLogging;
import dtomodels.models.ModelFileFormat;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.*;

@Epic("Api interface CDE")
@Feature("Раздел ModelRevisions(Ревизии моделей)")
@Story("Изменение ревизии модели в проекте")
public class DownloadModelRevisionFileTests extends ModelRevisionsBaseTests {

  private ValidatableResponse downloadModelRevisionResponse;
  private File resultFile;

  @BeforeEach
  public void setupMinimalLogging() {
    RestAssuredLogging.setupMinimalLogging();
  }

  @AfterEach
  @Step("Удаляем скачанный файл")
  public void deleteFile() {
    if (resultFile != null && resultFile.exists()) {
      resultFile.delete();
    }
    RestAssuredLogging.restoreOriginalFilters();
  }

  @Test
  @Tag(value = "positive")
  @DisplayName("Изменить ревизию модели - код ответа 200")
  public void downloadModelRevisionTest() throws IOException {
    ModelFileFormat modelFileFormat = IFC;
    int modelVersion = 3;
    downloadModelRevisionResponse = modelRevisionsClient
        .downloadModelRevisionFile(projectId, modelId, modelVersion, modelFileFormat);
    statusCode = extractStatusCode(downloadModelRevisionResponse);
    byte[] fileBytes = downloadModelRevisionResponse.extract().asByteArray();
    resultFile = saveModelRevisionFile(fileBytes, modelVersion, modelFileFormat);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertTrue(resultFile.exists()),
        () -> assertTrue(resultFile.length() > 0)
    );
  }
}
