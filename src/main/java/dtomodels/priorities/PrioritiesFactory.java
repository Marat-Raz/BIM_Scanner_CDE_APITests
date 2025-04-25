package dtomodels.priorities;

import dto.generated.CdeCreateOrUpdateTopicBoardPriorityDto;
import dtomodels.RandomWord;
import dtomodels.RandomColorInHex;

public class PrioritiesFactory {

  public String name = RandomWord.randomAllCharacters(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public CdeCreateOrUpdateTopicBoardPriorityDto createPriorities(PrioritiesType prioritiesType) {
    switch (prioritiesType) {
      case WITHOUT_NAME:
        return new CdeCreateOrUpdateTopicBoardPriorityDto(null, color);
      case WITHOUT_COLOR:
        return new CdeCreateOrUpdateTopicBoardPriorityDto(name, null);
      case DEFAULT:
      default:
        return new CdeCreateOrUpdateTopicBoardPriorityDto(name, color);
    }
  }

}
