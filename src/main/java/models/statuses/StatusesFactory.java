package models.statuses;

import models.RandomColorInHex;
import models.RandomWord;

public class StatusesFactory {

  public String name = RandomWord.randomAllCharacters(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public Statuses createStatuses(StatusesType statusesType) {
    switch (statusesType) {
      case WITHOUT_NAME:
        return new Statuses(null, color);
      case WITHOUT_COLOR:
        return new Statuses(name, null);
      case DEFAULT:
      default:
        return new Statuses(name, color);
    }
  }

}
