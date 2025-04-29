package dtomodels.customfields;

import static dto.generated.CdeCustomFieldType.*;
import static dtomodels.customfields.enumerationitem.EnumerationItemType.DEFAULT;

import dto.generated.CdeAddEnumerationCustomFieldItemDto;
import dto.generated.CdeCreateCustomFieldDto;
import dto.generated.CdeCustomFieldType;
import dtomodels.RandomWord;
import dtomodels.customfields.enumerationitem.EnumerationItemFactory;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomFieldFactory {

  private String name = RandomWord.randomAllCharacters(1, 256);
  private String description = RandomWord.randomAllCharacters(1, 100); // Can up to 10000 characters
  public CdeCustomFieldType type;
  public ArrayList<CdeAddEnumerationCustomFieldItemDto> enumerationItem =
      new ArrayList<>(Arrays.asList(
          new EnumerationItemFactory().createNameForEnumerationItem(DEFAULT),
          new EnumerationItemFactory().createNameForEnumerationItem(DEFAULT),
          new EnumerationItemFactory().createNameForEnumerationItem(DEFAULT)
      ));

  public CdeCreateCustomFieldDto createCustomField(CdeCustomFieldType customFieldType) {
    switch (customFieldType) {
      case INTEGER:
        return new CdeCreateCustomFieldDto(name, description, INTEGER, null);
      case DECIMAL:
        return new CdeCreateCustomFieldDto(name, description, DECIMAL, null);
      case DATE:
        return new CdeCreateCustomFieldDto(name, description, DATE, null);
      case BOOL:
        return new CdeCreateCustomFieldDto(name, description, BOOL, null);
      case ENUMERATION:
        return new CdeCreateCustomFieldDto(name, description, ENUMERATION, enumerationItem);
      case TEXT:
      default:
        return new CdeCreateCustomFieldDto(name, description, TEXT, null);
    }
  }

}

