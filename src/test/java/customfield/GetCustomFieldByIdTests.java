package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import dtomodels.customfields.ResponseCustomField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetCustomFieldByIdTests extends CustomFieldsBaseTests {

  private ValidatableResponse getResponse;

  // todo кастомные поля не удаляются с проекта, нужно в тестах это учитывать

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить кастомное поле проекта по его id")
  public void getCustomFieldByIdTest() {
    getResponse = customFieldsClient.getCustomFieldById(projectId, customFieldId);
    statusCode = extractStatusCode(getResponse);
    ResponseCustomField responseCustomField = getResponse.extract().as(ResponseCustomField.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(customFieldId, responseCustomField.getId());
  }

}
