package models.customfields.enumerationitem;

import models.RandomWord;

public class EnumerationItemFactory {

  private String name = RandomWord.randomAllCharacters(1, 256);

  public EnumerationItem createNameForEnumerationItem() {
    return new EnumerationItem(name);
  }

}
