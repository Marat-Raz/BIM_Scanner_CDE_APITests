package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeCustomFieldDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел CustomFields(Кастомные поля)")
@Story("Получение кастомного поля по id")
public class GetCustomFieldByIdTests extends CustomFieldsBaseTests {

  private ValidatableResponse getResponse;

  // todo кастомные поля не удаляются с проекта, нужно в тестах это учитывать

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить кастомное поле проекта по его id")
  public void getCustomFieldByIdTest() {
    getResponse = customFieldsClient.getCustomFieldById(projectId, customFieldId);
    statusCode = extractStatusCode(getResponse);
    CdeCustomFieldDto cdeCustomFieldDto = getResponse.extract().as(CdeCustomFieldDto.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(customFieldId, cdeCustomFieldDto.getId());
  }

}
