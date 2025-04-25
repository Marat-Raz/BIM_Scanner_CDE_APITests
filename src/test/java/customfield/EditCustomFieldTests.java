package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import dto.generated.CdeUpdateCustomFieldDto;
import dto.generated.CdeCustomFieldDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел CustomFields(Кастомные поля)")
@Story("Редактирование кастомного поля")
public class EditCdeCreateCustomFieldDtoTests extends CustomFieldsBaseTests {

  private ValidatableResponse editResponse;
  private CdeUpdateCustomFieldDto updateCustomField;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить параметры кастомного поля")
  public void editCustomFieldsTest() {
    updateCustomField = CdeUpdateCustomFieldDto.from(customFieldArrayList.get(0));
    updateCustomField.setName("New name");
    editResponse = customFieldsClient.editCustomField(projectId, customFieldId,
        updateCustomField);
    statusCode = extractStatusCode(editResponse);
    CdeCustomFieldDto cdeCustomFieldDto = editResponse.extract().as(CdeCustomFieldDto.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(updateCustomField.getName(), cdeCustomFieldDto.getName());
  }

  // todo Edit custom field parameters. Custom field can not be deleted, only archived.
  //  Archived custom field may be restored.
}
