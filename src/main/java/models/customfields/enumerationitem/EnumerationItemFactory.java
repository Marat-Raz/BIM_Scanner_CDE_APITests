package models.customfields.enumerationitem;

import org.apache.commons.lang3.RandomStringUtils;

public class EnumerationItemFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);

  public EnumerationItem createNameForEnumerationItem() {
    return new EnumerationItem(name);
  }

}
