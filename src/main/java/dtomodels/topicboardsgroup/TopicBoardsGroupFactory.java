package dtomodels.topicboardsgroup;

import dto.generated.CdeCreateTopicBoardGroupDto;
import java.util.UUID;
import dtomodels.RandomWord;

public class TopicBoardsGroupFactory {

  private final String name = RandomWord.randomAllCharacters(1, 256);
  private final String wrongParentGroupId = String.valueOf(UUID.randomUUID());

  public CdeCreateTopicBoardGroupDto createTopicBoardsGroup(TopicBoardsGroupType topicBoardsGroupType) {
    switch (topicBoardsGroupType) {
      case TOPIC_BOARDS_GROUP_WITHOUT_NAME:
        return new CdeCreateTopicBoardGroupDto(null, wrongParentGroupId);
      case NEW_TOPIC_BOARDS_GROUP:
        return new CdeCreateTopicBoardGroupDto(RandomWord.randomAllCharacters(1, 256), null);
      case DEFAULT_TOPIC_BOARDS_GROUP:
      default:
        return new CdeCreateTopicBoardGroupDto(name, null);
    }
  }

}
