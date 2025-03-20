package models.types;

import models.RandomColorInHex;
import models.RandomWord;

public class TypesFactory {

  public String name = RandomWord.randomAllCharacters(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public Types createTypes(TypesType typesType) {
    switch (typesType) {
      case WITHOUT_NAME:
        return new Types(null, color);
      case WITHOUT_COLOR:
        return new Types(name, null);
      case DEFAULT:
      default:
        return new Types(name, color);
    }
  }

}
