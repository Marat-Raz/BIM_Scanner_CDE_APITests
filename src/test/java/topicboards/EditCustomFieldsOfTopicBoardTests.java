package topicboards;

import static dtomodels.customfields.updatetopicboardcustomfields.CustomFieldOnTopicBoardsType.IS_ENABLED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.*;
import dtomodels.customfields.updatetopicboardcustomfields.CustomFieldOnTopicBoardsFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoards(Доски задач)")
@Story("Редактирование кастомных полей в доске задач")
public class EditCustomFieldsOfTopicBoardTests extends CreateTopicBoardBaseTests {

  private static ValidatableResponse editCustomFieldResponse;
  private static ValidatableResponse patchTopicBoardResponse;
  private static ArrayList<CdeTopicBoardCustomFieldDto> existsCustomFields = new ArrayList<>();

  @BeforeEach
  @Step("Добавить кастомные поля в доску задач")
  public void createProject() {
    CustomFieldOnTopicBoardsFactory customFieldOnTopicBoardsFactory = new CustomFieldOnTopicBoardsFactory();
    CdeModifyTopicBoardCustomFieldDto customField = customFieldOnTopicBoardsFactory
        .createCustomFieldOnTopicBoardsById(customFieldId, IS_ENABLED);

    ArrayList<CdeModifyTopicBoardCustomFieldDto> customFieldArray = new ArrayList<>();
    customFieldArray.add(customField);

    CdeUpdateTopicBoardCustomFieldsDto cdeUpdateTopicBoardCustomFieldsDto =
        new CdeUpdateTopicBoardCustomFieldsDto(customFieldArray);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, cdeUpdateTopicBoardCustomFieldsDto);

    patchTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    responseTopicBoard = patchTopicBoardResponse.extract()
        .as(CdeTopicBoardDto.class);

    existsCustomFields = responseTopicBoard.getCustomFields();
  }

  @Test
  @DisplayName("Редактирование кастомных полей в доске задач")
  public void editCustomFieldsOfTopicBoardTest() {
    CdeTopicBoardCustomFieldDto editableCustomField = existsCustomFields.get(0);
    String expectedTxt = "new Test Value";
    editableCustomField.setDefaultValue(expectedTxt);
    existsCustomFields.set(0, editableCustomField);

    ArrayList<CdeModifyTopicBoardCustomFieldDto> editableCustomFieldArray = new ArrayList<>();
    editableCustomFieldArray.add(CdeTopicBoardCustomFieldDto.convert(editableCustomField));

    CdeUpdateTopicBoardCustomFieldsDto cdeUpdateTopicBoardCustomFieldsDto = new CdeUpdateTopicBoardCustomFieldsDto(
        editableCustomFieldArray);

    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, cdeUpdateTopicBoardCustomFieldsDto);
    responseTopicBoard = editCustomFieldResponse.extract()
        .as(CdeTopicBoardDto.class);

    ArrayList<CdeTopicBoardCustomFieldDto> actualCustomField = responseTopicBoard.getCustomFields();

    assertAll(
        () -> assertEquals(expectedTxt, actualCustomField.get(0).getDefaultValue())
    );

  }

// комментарии будут удалены позже.
// todo создать проект, создать кастомные поля в проекте, создать доску задач,
//  получить список кастомных полей в этом проекте, переедать в запросе все кастомные поля,
//  включая те, которые хотим изменить

}
