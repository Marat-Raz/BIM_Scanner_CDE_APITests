package modelrevisions;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел ModelRevisions(Ревизии моделей)")
@Story("Загрузка нового файла модели")
public class UploadModelFileTests extends ModelRevisionsBaseTests {

  @Test
  @Tag(value = "positive")
  @DisplayName("Загрузить новый файл модели - код ответа 200")
  public void uploadModelFileTest() {
    statusCode = extractStatusCode(uploadModelFileResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(modelId, cdeModelRevisionDto.getModelId())
    );
  }
}
