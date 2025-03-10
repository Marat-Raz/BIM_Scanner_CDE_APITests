package customfield;

import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.CustomFieldsClient;
import io.restassured.response.ValidatableResponse;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetCustomFieldsTests extends StartTests {

  private static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  private ValidatableResponse getResponse;
  private Map<String, Object> queryParams = new HashMap<>();

  @BeforeEach
  public void setUp() {
    // Здесь можно добавить подготовку данных, если необходимо
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список кастомных полей проекта")
  public void getCustomFieldsTest() {
    // Параметры запроса (например, фильтр по архивированным полям)
    queryParams.put("archive", "not-archived"); // Получаем только неархивированные поля

    // Выполняем запрос
    getResponse = customFieldsClient.getCustomFields(projectId, queryParams);

    // Проверяем статус код
    statusCode = extractStatusCode(getResponse);
    assertEquals(200, statusCode);

    // Проверяем, что ответ содержит список кастомных полей
    List<ResponseCustomField> customFields = getResponse.extract().jsonPath().getList("", ResponseCustomField.class);

    // Пример проверок:
    assertThat(customFields, is(not(empty()))); // Проверяем, что список не пустой
    for (ResponseCustomField field : customFields) {
      assertThat(field.getId(), is(notNullValue())); // Проверяем, что у каждого поля есть ID
      assertThat(field.getName(), is(notNullValue())); // Проверяем, что у каждого поля есть имя
    }
  }
}