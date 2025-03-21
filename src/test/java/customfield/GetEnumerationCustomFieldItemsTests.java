package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import models.customfields.enumerationitem.ResponseEnumerationItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetEnumerationCustomFieldItemsTests extends CustomFieldsBaseTests {

  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получение элементов кастомных полей перечисления.")
  public void getEnumerationCustomFieldItemsTest() {
    getResponse = customFieldsClient.getCustomFieldEnumerationItems(projectId, customFieldId);
    statusCode = extractStatusCode(getResponse);
    ResponseEnumerationItem[] responseEnumerationItems = getResponse.extract()
        .as(ResponseEnumerationItem[].class);

    assertEquals(SC_OK, statusCode);
    assertEquals(customFieldsCount, responseEnumerationItems.length);
  }
}
