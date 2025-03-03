package models.priorities;

import models.RandomColorInHex;
import org.apache.commons.lang3.RandomStringUtils;

public class PrioritiesFactory {

  public String name = RandomStringUtils.randomAlphabetic(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public Priorities createPriorities(PrioritiesType prioritiesType) {
    switch (prioritiesType) {
      case WITHOUT_NAME:
        return new Priorities(null, color);
      case WITHOUT_COLOR:
        return new Priorities(name, null);
      default:
      case DEFAULT:
        return new Priorities(name, color);
    }
  }

}
