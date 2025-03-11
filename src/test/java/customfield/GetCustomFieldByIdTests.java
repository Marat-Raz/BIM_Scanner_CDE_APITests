package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.customfields.ResponseCustomField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetCustomFieldByIdTests extends CustomFieldsBaseTests {

  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить кастомное поле проекта по его id")
  public void getCustomFieldByIdTest() {
    getResponse = customFieldsClient.getCustomFieldById(projectId, customFieldId);
    statusCode = extractStatusCode(getResponse);
    ResponseCustomField responseCustomField = getResponse.extract().as(ResponseCustomField.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(customFieldId, responseCustomField.getId()),
        () -> assertEquals(responseCustomFieldArrayList.get(0).getName(),
            responseCustomField.getName()),
        () -> assertEquals(responseCustomFieldArrayList.get(0).getEnumerationItems().size(),
        responseCustomField.getEnumerationItems().size())
    );

  }

}
