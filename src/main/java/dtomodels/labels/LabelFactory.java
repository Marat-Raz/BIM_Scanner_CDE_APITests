package dtomodels.labels;

import dtomodels.RandomColorInHex;
import dtomodels.RandomWord;

public class LabelFactory {

  public String name = RandomWord.randomLatinCharacters(1, 256);
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
