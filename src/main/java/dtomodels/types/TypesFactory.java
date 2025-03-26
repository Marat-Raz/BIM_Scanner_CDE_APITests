package dtomodels.types;

import dtomodels.RandomWord;
import dtomodels.RandomColorInHex;

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
