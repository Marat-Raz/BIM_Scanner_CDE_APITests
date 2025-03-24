package dtomodels.priorities;

import dtomodels.RandomWord;
import dtomodels.RandomColorInHex;

public class PrioritiesFactory {

  public String name = RandomWord.randomAllCharacters(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public Priorities createPriorities(PrioritiesType prioritiesType) {
    switch (prioritiesType) {
      case WITHOUT_NAME:
        return new Priorities(null, color);
      case WITHOUT_COLOR:
        return new Priorities(name, null);
      case DEFAULT:
      default:
        return new Priorities(name, color);
    }
  }

}
