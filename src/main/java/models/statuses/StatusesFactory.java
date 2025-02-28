package models.statuses;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class StatusesFactory {

  public String name = RandomStringUtils.randomAlphabetic(1, 256);
  public String color = getRandomColorInHex();

  public Statuses createStatuses(StatusesType statusesType) {
    switch (statusesType) {
      case WITHOUT_NAME:
        return new Statuses(null, color);
      case WITHOUT_COLOR:
        return new Statuses(name, null);
      default:
      case DEFAULT:
        return new Statuses(name, color);
    }
  }

  public String getRandomColorInHex() {
    Random random = new Random();
    int alpha = random.nextInt(256);
    int red = random.nextInt(256);
    int green = random.nextInt(256);
    int blue = random.nextInt(256);

    String hexAlpha = String.format("%02X", alpha);
    String hexRed = String.format("%02X", red);
    String hexGreen = String.format("%02X", green);
    String hexBlue = String.format("%02X", blue);

    return "#" + hexAlpha + hexRed + hexGreen + hexBlue;
  }
}
