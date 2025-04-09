package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import dtomodels.customfields.customfieldstoedit.CustomFieldToEdit;
import dtomodels.customfields.ResponseCustomField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел CustomFields(Кастомные поля)")
@Story("Редактирование кастомного поля")
public class EditCustomFieldTests extends CustomFieldsBaseTests {

  private ValidatableResponse editResponse;
  private CustomFieldToEdit customFieldToEdit;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Изменить параметры кастомного поля")
  public void editCustomFieldsTest() {
    customFieldToEdit = CustomFieldToEdit.from(responseCustomFieldArrayList.get(0));
    customFieldToEdit.setName("New name");
    editResponse = customFieldsClient.editCustomField(projectId, customFieldId, customFieldToEdit);
    statusCode = extractStatusCode(editResponse);
    ResponseCustomField responseCustomField = editResponse.extract().as(ResponseCustomField.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(customFieldToEdit.getName(), responseCustomField.getName());
  }

  // todo Edit custom field parameters. Custom field can not be deleted, only archived.
  //  Archived custom field may be restored.
}
