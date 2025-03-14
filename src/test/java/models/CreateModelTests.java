package models;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateModelTests extends ModelsBaseTests {

  private ValidatableResponse createModelResponse; // создан на перспективу для других тестов

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на создание модели дает ответ 200")
  public void createModelTest() {
    statusCode = extractStatusCode(createResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(defaultModel.getName(), defaultResponseModel.getName()),
        () -> assertNotNull(defaultResponseModel.getId())
    );
  }
}
