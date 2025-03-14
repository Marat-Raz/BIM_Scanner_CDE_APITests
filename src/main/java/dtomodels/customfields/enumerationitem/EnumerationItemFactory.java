package dtomodels.customfields.enumerationitem;

import org.apache.commons.lang3.RandomStringUtils;

public class EnumerationItemFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);

  public EnumerationItem createNameForEnumerationItem(EnumerationItemType enumerationItemType) {
    switch (enumerationItemType) {
      case NAME_IS_NULL:
        return new EnumerationItem(null);
      case DEFAULT:
      default:
        return new EnumerationItem(name);
    }
  }
// todo добавить другие типы EnumerationItem для негативных тестов
}
