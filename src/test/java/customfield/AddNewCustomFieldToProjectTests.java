package customfield;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dto.generated.CdeCustomFieldDto;
import dto.generated.CdeAddEnumerationCustomFieldItemDto;
import dto.generated.CdeEnumerationCustomFieldItemDto;
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
public class AddNewCdeCreateCustomFieldDtoToCdeCreateProjectDtoTests extends CustomFieldsBaseTests {

  private ValidatableResponse addResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавить новое кастомное поле в проект")
  public void addNewCustomFieldToProjectTest() {
    addResponse = customFieldsClient.addNewCustomFieldToProject(projectId, cdeCreateCustomFieldDto);
    statusCode = extractStatusCode(addResponse);
    CdeCustomFieldDto cdeCustomFieldDto = addResponse.extract().as(CdeCustomFieldDto.class);
    ArrayList<CdeEnumerationCustomFieldItemDto> cdeEnumerationCustomFieldItemDtos =
        cdeCustomFieldDto.getEnumerationItems();

    List<CdeAddEnumerationCustomFieldItemDto> cdeAddEnumerationCustomFieldItemDtos = cdeCreateCustomFieldDto.getCdeAddEnumerationCustomFieldItemDtos();
    List<String> expectedEnumerationItemName = new ArrayList<>();
    for (CdeAddEnumerationCustomFieldItemDto cdeAddEnumerationCustomFieldItemDto : cdeAddEnumerationCustomFieldItemDtos) {
      expectedEnumerationItemName.add(cdeAddEnumerationCustomFieldItemDto.getName());
    }

    List<String> actualEnumerationItemName = new ArrayList<>();
    for (CdeEnumerationCustomFieldItemDto enumerationItem : cdeEnumerationCustomFieldItemDtos) {
      actualEnumerationItemName.add(enumerationItem.getName());
    }

    Collections.sort(expectedEnumerationItemName);
    Collections.sort(actualEnumerationItemName);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(cdeCustomFieldDto.getType(),
            cdeCreateCustomFieldDto.getType().toString().toLowerCase()),
        () -> assertEquals(cdeCustomFieldDto.getName(), cdeCreateCustomFieldDto.getName()),
        () -> assertTrue(expectedEnumerationItemName.equals(actualEnumerationItemName))

    );
  }
  // todo реализовать получение id кастомных полей
  // todo реализовать () -> assertEquals(new HashSet<>(expectedStatuses), new HashSet<>(actualStatuses))
  //  как в GetTopicBoardCdeCreateOrUpdateTopicBoardStatusDtoTests

}
