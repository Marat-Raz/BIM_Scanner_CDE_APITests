package models.customfields;

import static models.customfields.CustomFieldType.*;

import java.util.List;
import models.customfields.enumerationitem.EnumerationItem;
import models.customfields.enumerationitem.EnumerationItemFactory;
import org.apache.commons.lang3.RandomStringUtils;

public class CustomFieldFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);
  private String description = RandomStringUtils.randomAlphabetic(1,
      100); // Can up to 10000 characters
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
      default:
      case TEXT:
        return new CustomField(name, description, TEXT, null);
    }
  }

}

