package dtomodels.customfields.enumerationitem;

import dto.generated.CdeAddEnumerationCustomFieldItemDto;
import org.apache.commons.lang3.RandomStringUtils;

public class EnumerationItemFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);

  public CdeAddEnumerationCustomFieldItemDto createNameForEnumerationItem(EnumerationItemType enumerationItemType) {
    switch (enumerationItemType) {
      case NAME_IS_NULL:
        return new CdeAddEnumerationCustomFieldItemDto(null);
      case DEFAULT:
      default:
        return new CdeAddEnumerationCustomFieldItemDto(name);
    }
  }
// todo добавить другие типы EnumerationItem для негативных тестов
}
