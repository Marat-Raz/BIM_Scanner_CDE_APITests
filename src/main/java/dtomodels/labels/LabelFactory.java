package dtomodels.labels;

import dto.generated.CdeCreateLabelDto;
import dto.generated.CdeLabelDto;
import dtomodels.RandomColorInHex;
import dtomodels.RandomWord;

public class LabelFactory {

  public String name = RandomWord.randomLatinCharacters(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public CdeCreateLabelDto createLabel(LabelType labelType) {
    switch (labelType) {
      case WITHOUT_NAME:
        return new CdeCreateLabelDto(null, color);
      case WITHOUT_COLOR:
        return new CdeCreateLabelDto(name, null);
      case DEFAULT:
      default:
        return new CdeCreateLabelDto(name, color);
    }
  }

  public CdeCreateLabelDto from(CdeLabelDto cdeLabelDto) {
    return new CdeCreateLabelDto(cdeLabelDto.getName(), cdeLabelDto.getColor());
  }

}
