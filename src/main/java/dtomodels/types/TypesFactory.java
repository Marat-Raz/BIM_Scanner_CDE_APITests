package dtomodels.types;

import dto.generated.CdeCreateOrUpdateTopicBoardTypeDto;
import dtomodels.RandomWord;
import dtomodels.RandomColorInHex;

public class TypesFactory {

  public String name = RandomWord.randomAllCharacters(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public CdeCreateOrUpdateTopicBoardTypeDto createTypes(TypesType typesType) {
    switch (typesType) {
      case WITHOUT_NAME:
        return new CdeCreateOrUpdateTopicBoardTypeDto(null, color);
      case WITHOUT_COLOR:
        return new CdeCreateOrUpdateTopicBoardTypeDto(name, null);
      case DEFAULT:
      default:
        return new CdeCreateOrUpdateTopicBoardTypeDto(name, color);
    }
  }

}
