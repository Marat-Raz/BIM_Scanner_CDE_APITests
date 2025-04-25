package models;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Models(Модели)")
@Story("Создание модели в проекте")
public class CreateCdeCreateOrUpdateModelDtoTests extends ModelsBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на создание модели дает ответ 200")
  public void createModelTest() {
    statusCode = extractStatusCode(createResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(defaultCdeCreateOrUpdateModelDto.getName(), defaultCdeModelDto.getName()),
        () -> assertNotNull(defaultCdeModelDto.getId())
    );
  }
}
