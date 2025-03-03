package models;

import java.util.Random;

public class RandomColor {

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
