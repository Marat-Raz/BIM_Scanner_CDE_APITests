package models.customfields;

import static models.customfields.CustomFieldType.*;

import java.util.List;
import models.RandomWord;
import models.customfields.enumerationitem.EnumerationItem;
import models.customfields.enumerationitem.EnumerationItemFactory;

public class CustomFieldFactory {

  private String name = RandomWord.randomAllCharacters(1, 256);
  private String description = RandomWord.randomAllCharacters(1, 100); // Can up to 10000 characters
  public CustomFieldType type;
  public List<EnumerationItem> enumerationItems =
      List.of(new EnumerationItemFactory().createNameForEnumerationItem(),
          new EnumerationItemFactory().createNameForEnumerationItem(),
          new EnumerationItemFactory().createNameForEnumerationItem());

  public CustomField createCustomField(CustomFieldType customFieldType) {
    switch (customFieldType) {
      case INTEGER:
        return new CustomField(name, description, INTEGER, null);
      case DECIMAL:
        return new CustomField(name, description, DECIMAL, null);
      case DATE:
        return new CustomField(name, description, DATE, null);
      case BOOL:
        return new CustomField(name, description, BOOL, null);
      case ENUMERATION:
        return new CustomField(name, description, ENUMERATION, enumerationItems);
      case TEXT:
      default:
        return new CustomField(name, description, TEXT, null);
    }
  }

}

