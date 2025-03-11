package models.customfields.customfieldsintopicbords;

public class CustomFieldOnTopicBoardsFactory {

  public String id;
  public boolean isEnabled;
  public boolean isRequired;
  public String defaultValue = "string"; //

  public CustomFieldOnTopicBoards createCustomFieldOnTopicBoardsById(String idFromResponse,
      CustomFieldOnTopicBoardsType customFieldOnTopicBoardsType) {
    switch (customFieldOnTopicBoardsType) {
      case IS_NOT_ENABLED:
        return new CustomFieldOnTopicBoards(idFromResponse, false, true, null);

      default:
      case DEFAULT_CUSTOM_FIELDS_TO_EDIT:
      case IS_ENABLED:
        return new CustomFieldOnTopicBoards(idFromResponse, true, true, defaultValue);}

  }
}
