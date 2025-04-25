package dtomodels.customfields.updatetopicboardcustomfields;

import dto.generated.CdeModifyTopicBoardCustomFieldDto;

public class CustomFieldOnTopicBoardsFactory {

  public String id;
  public String defaultValue = "string";

  public CdeModifyTopicBoardCustomFieldDto createCustomFieldOnTopicBoardsById(String idFromResponse,
      CustomFieldOnTopicBoardsType customFieldOnTopicBoardsType) {
    switch (customFieldOnTopicBoardsType) {
      case IS_NOT_ENABLED:
        return new CdeModifyTopicBoardCustomFieldDto(idFromResponse, false, true, null);

      case DEFAULT_CUSTOM_ON_TOPIC_BOARDS:
      case IS_ENABLED:
      default:
        return new CdeModifyTopicBoardCustomFieldDto(idFromResponse, true, true, defaultValue);
    }

  }
}
