package topicboards;

import static dtomodels.customfields.updatetopicboardcustomfields.CustomFieldOnTopicBoardsType.IS_ENABLED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeModifyTopicBoardCustomFieldDto;
import dtomodels.customfields.updatetopicboardcustomfields.CustomFieldOnTopicBoardsFactory;
import dto.generated.CdeUpdateTopicBoardCustomFieldsDto;
import dtomodels.topicboards.ResponseTopicBoards;
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
public class EditCustomFieldsOfTopicBoardTests extends TopicBoardsBaseTests {

  private static ValidatableResponse editCustomFieldResponse;
  private static ValidatableResponse getTopicBoardResponse;
  private static ArrayList<CdeModifyTopicBoardCustomFieldDto> existsCustomFields = new ArrayList<>();

  @BeforeEach
  @Step("Добавить кастомные поля в доску задач")
  public void createProject() {
    CustomFieldOnTopicBoardsFactory customFieldOnTopicBoardsFactory = new CustomFieldOnTopicBoardsFactory();
    CdeModifyTopicBoardCustomFieldDto cdeModifyTopicBoardCustomFieldDto = customFieldOnTopicBoardsFactory
        .createCustomFieldOnTopicBoardsById(customFieldId, IS_ENABLED);

    CdeUpdateTopicBoardCustomFieldsDto cdeUpdateTopicBoardCustomFieldsDto = new CdeUpdateTopicBoardCustomFieldsDto(
        cdeModifyTopicBoardCustomFieldDto);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, cdeUpdateTopicBoardCustomFieldsDto);

    getTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    responseTopicBoard = getTopicBoardResponse.extract()
        .as(ResponseTopicBoards.class);

    existsCustomFields = responseTopicBoard.getCustomFields();

  }

  @Test
  @DisplayName("Редактирование кастомных полей в доске задач")
  public void editCustomFieldsOfTopicBoardTest() {
    CdeModifyTopicBoardCustomFieldDto editableCustomField = existsCustomFields.get(0);
    String expectedTxt = "new Test Value";
    editableCustomField.setDefaultValue(expectedTxt);

    existsCustomFields.set(0, editableCustomField);
    CdeUpdateTopicBoardCustomFieldsDto cdeUpdateTopicBoardCustomFieldsDto = new CdeUpdateTopicBoardCustomFieldsDto(
        existsCustomFields);
    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, cdeUpdateTopicBoardCustomFieldsDto);
    ResponseTopicBoards responseTopicBoards = editCustomFieldResponse.extract()
        .as(ResponseTopicBoards.class);

    ArrayList<CdeModifyTopicBoardCustomFieldDto> actualCustomField = responseTopicBoards.getCustomFields();

    assertAll(
        () -> assertEquals(expectedTxt, actualCustomField.get(0).getDefaultValue())
    );

  }

// комментарии будут удалены позже.
// todo создать проект, создать кастомные поля в проекте, создать доску задач,
//  получить список кастомных полей в этом проекте, переедать в запросе все кастомные поля,
//  включая те, которые хотим изменить

}
