package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import client.CustomFieldsClient;
import io.restassured.response.ValidatableResponse;
import java.util.HashMap;
import java.util.Map;
import models.customfields.ResponseCustomField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetCustomFieldsTests extends CustomFieldsBaseTests {

  private ValidatableResponse getResponse;
  private Map<String, String> queryParams = new HashMap<>();
  private String[] archiveType = new String[]{"all", "archived", "not-archived"};

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список кастомных полей проекта")
  public void getCustomFieldsTest() {
    queryParams.put("archive", archiveType[0]);
    getResponse = customFieldsClient.getCustomFields(projectId, queryParams);
    statusCode = extractStatusCode(getResponse);
    ResponseCustomField[] arrayOfCustomField = getResponse.extract().as(ResponseCustomField[].class);

    assertEquals(SC_OK, statusCode);
    assertEquals(responseCustomFieldArrayList.size(), arrayOfCustomField.length);
  }
}