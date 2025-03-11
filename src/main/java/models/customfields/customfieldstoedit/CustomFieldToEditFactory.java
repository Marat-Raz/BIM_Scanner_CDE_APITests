package models.customfields.customfieldstoedit;

public class CustomFieldToEditFactory {

  public String id;
  public boolean isEnabled;
  public boolean isRequired;
  public String defaultValue = "string"; //

  public CustomFieldToEdit createCustomFieldToEditById(String idFromResponse,
      CustomFieldToEditType customFieldToEditType) {
    switch (customFieldToEditType) {
      case IS_NOT_ENABLED:
        return new CustomFieldToEdit(idFromResponse, false, true, null);

      case DEFAULT_CUSTOM_FIELDS_TO_EDIT:
      case IS_ENABLED:
      default:
        return new CustomFieldToEdit(idFromResponse, true, true, defaultValue);}

  }
}
