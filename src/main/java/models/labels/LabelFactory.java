package models.labels;

import models.RandomColorInHex;
import org.apache.commons.lang3.RandomStringUtils;

public class LabelFactory {

  public String name = RandomStringUtils.randomAlphabetic(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public Label createLabel(LabelType labelType) {
    switch (labelType) {
      case WITHOUT_NAME:
        return new Label(null, color);
      case WITHOUT_COLOR:
        return new Label(name, null);
      case DEFAULT:
      default:
        return new Label(name, color);
    }
  }
  public Label from(ResponseLabel responseLabel) {
    return new Label(responseLabel.getName(), responseLabel.getColor());
  }
}
