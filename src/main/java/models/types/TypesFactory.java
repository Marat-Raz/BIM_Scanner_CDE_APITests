package models.types;

import models.RandomColorInHex;
import org.apache.commons.lang3.RandomStringUtils;

public class TypesFactory {

  public String name = RandomStringUtils.randomAlphabetic(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public Types createTypes(TypesType typesType) {
    switch (typesType) {
      case WITHOUT_NAME:
        return new Types(null, color);
      case WITHOUT_COLOR:
        return new Types(name, null);
      default:
      case DEFAULT:
        return new Types(name, color);
    }
  }

}
