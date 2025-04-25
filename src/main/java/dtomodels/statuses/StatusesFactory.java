package dtomodels.statuses;

import dto.generated.CdeCreateOrUpdateTopicBoardStatusDto;
import dtomodels.RandomWord;
import dtomodels.RandomColorInHex;

public class StatusesFactory {

  public String name = RandomWord.randomAllCharacters(1, 256);
  public String color = RandomColorInHex.getRandomColorInHex();

  public CdeCreateOrUpdateTopicBoardStatusDto createStatuses(StatusesType statusesType) {
    switch (statusesType) {
      case WITHOUT_NAME:
        return new CdeCreateOrUpdateTopicBoardStatusDto(null, color);
      case WITHOUT_COLOR:
        return new CdeCreateOrUpdateTopicBoardStatusDto(name, null);
      case DEFAULT:
      default:
        return new CdeCreateOrUpdateTopicBoardStatusDto(name, color);
    }
  }

}
