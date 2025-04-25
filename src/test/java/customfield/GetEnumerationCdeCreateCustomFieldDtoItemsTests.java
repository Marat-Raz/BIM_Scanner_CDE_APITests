package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeEnumerationCustomFieldItemDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел CustomFields(Кастомные поля)")
@Story("Получение элементов кастомных полей перечисления")
public class GetEnumerationCdeCreateCustomFieldDtoItemsTests extends CustomFieldsBaseTests {

  private ValidatableResponse getResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получение элементов кастомных полей перечисления - код 200")
  public void getEnumerationCustomFieldItemsTest() {
    getResponse = customFieldsClient.getCustomFieldEnumerationItems(projectId, customFieldId);
    statusCode = extractStatusCode(getResponse);
    CdeEnumerationCustomFieldItemDto[] cdeEnumerationCustomFieldItemDtos = getResponse.extract()
        .as(CdeEnumerationCustomFieldItemDto[].class);

    assertEquals(SC_OK, statusCode);
    assertEquals(customFieldsCount, cdeEnumerationCustomFieldItemDtos.length);
  }
}
