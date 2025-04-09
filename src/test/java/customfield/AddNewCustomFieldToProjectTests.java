package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtomodels.customfields.ResponseCustomField;
import dtomodels.customfields.enumerationitem.EnumerationItem;
import dtomodels.customfields.enumerationitem.ResponseEnumerationItem;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел CustomFields(Кастомные поля)")
@Story("Добавление кастомного поля")
public class AddNewCustomFieldToProjectTests extends CustomFieldsBaseTests {

  private ValidatableResponse addResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавить новое кастомное поле в проект")
  public void addNewCustomFieldToProjectTest() {
    addResponse = customFieldsClient.addNewCustomFieldToProject(projectId, customField);
    statusCode = extractStatusCode(addResponse);
    ResponseCustomField responseCustomField = addResponse.extract().as(ResponseCustomField.class);
    ArrayList<ResponseEnumerationItem> responseEnumerationItems =
        responseCustomField.getEnumerationItems();

    List<EnumerationItem> enumerationItems = customField.getEnumerationItems();
    List<String> expectedEnumerationItemName = new ArrayList<>();
    for (EnumerationItem enumerationItem : enumerationItems) {
      expectedEnumerationItemName.add(enumerationItem.getName());
    }

    List<String> actualEnumerationItemName = new ArrayList<>();
    for (ResponseEnumerationItem enumerationItem : responseEnumerationItems) {
      actualEnumerationItemName.add(enumerationItem.getName());
    }

    Collections.sort(expectedEnumerationItemName);
    Collections.sort(actualEnumerationItemName);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(responseCustomField.getType(),
            customField.getType().toString().toLowerCase()),
        () -> assertEquals(responseCustomField.getName(), customField.getName()),
        () -> assertTrue(expectedEnumerationItemName.equals(actualEnumerationItemName))

    );
  }
  // todo реализовать получение id кастомных полей
  // todo реализовать () -> assertEquals(new HashSet<>(expectedStatuses), new HashSet<>(actualStatuses))
  //  как в GetTopicBoardStatusesTests

}
