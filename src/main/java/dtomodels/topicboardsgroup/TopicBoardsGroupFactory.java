package dtomodels.topicboardsgroup;

import java.util.UUID;
import dtomodels.RandomWord;

public class TopicBoardsGroupFactory {

  private final String name = RandomWord.randomAllCharacters(1, 256);
  private final String wrongParentGroupId = String.valueOf(UUID.randomUUID());

  public TopicBoardsGroup createTopicBoardsGroup(TopicBoardsGroupType topicBoardsGroupType) {
    switch (topicBoardsGroupType) {
      case TOPIC_BOARDS_GROUP_WITHOUT_NAME:
        return new TopicBoardsGroup(null, wrongParentGroupId);
      case NEW_TOPIC_BOARDS_GROUP:
        return new TopicBoardsGroup(RandomWord.randomAllCharacters(1, 256), null);
      case DEFAULT_TOPIC_BOARDS_GROUP:
      default:
        return new TopicBoardsGroup(name, null);
    }
  }

}
