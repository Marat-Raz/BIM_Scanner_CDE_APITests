package topicboards;

import static dtomodels.customfields.updatetopicboardcustomfields.CustomFieldOnTopicBoardsType.IS_ENABLED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeModifyTopicBoardCustomFieldDto;
import dto.generated.CdeTopicBoardCustomFieldDto;
import dto.generated.CdeTopicBoardDto;
import dto.generated.CdeUpdateTopicBoardCustomFieldsDto;
import dtomodels.customfields.updatetopicboardcustomfields.CustomFieldOnTopicBoardsFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoards(Доски задач)")
@Story("Добавление кастомных полей в доску задач")
public class AddCustomFieldsToTopicBoardTests extends CreateTopicBoardBaseTests {

  private static ValidatableResponse getTopicBoardResponse;
  private static ArrayList<CdeTopicBoardCustomFieldDto> existsCustomFields;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавление кастомных полей в доску задач")
  public void editCustomFieldsOfTopicBoardTest() {
    CustomFieldOnTopicBoardsFactory customFieldOnTopicBoardsFactory = new CustomFieldOnTopicBoardsFactory();
    CdeModifyTopicBoardCustomFieldDto customField = customFieldOnTopicBoardsFactory
        .createCustomFieldOnTopicBoardsById(customFieldId, IS_ENABLED);

    ArrayList<CdeModifyTopicBoardCustomFieldDto> fieldsList = new ArrayList<>();
    fieldsList.add(customField);
    CdeUpdateTopicBoardCustomFieldsDto cdeUpdateTopicBoardCustomFieldsDto =
        new CdeUpdateTopicBoardCustomFieldsDto(fieldsList);

    editCustomFieldResponse = topicBoardsClient
        .editTopicBoardCustomFields(projectId, topicBoardId, cdeUpdateTopicBoardCustomFieldsDto);

    getTopicBoardResponse = topicBoardsClient.getTopicBoard(projectId, topicBoardId);
    responseTopicBoard = getTopicBoardResponse.extract()
        .as(CdeTopicBoardDto.class);

    existsCustomFields = responseTopicBoard.getCustomFields();

    assertAll(
        () -> assertEquals(customFieldId, existsCustomFields.get(0).getId())
    );
  }

// комментарии будут удалены позже.
//  todo создать проект, создать кастомные поля в проекте, создать доску задач,
//   получить список кастомных полей в этом проекте, переедать в запросе все кастомные поля,
//   включая те, которые хотим изменить

}
