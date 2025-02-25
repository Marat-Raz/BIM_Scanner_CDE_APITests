package models.topicboardsgroup;

import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;

public class TopicBoardsGroupFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);
  private String wrongParentGroupId = String.valueOf(UUID.randomUUID());

  public TopicBoardsGroup createTopicBoardsGroup(TopicBoardsGroupType topicBoardsGroupType) {
    switch (topicBoardsGroupType) {
      case TOPIC_BOARDS_GROUP_WITHOUT_NAME:
        return new TopicBoardsGroup(null, wrongParentGroupId);
        case NEW_TOPIC_BOARDS_GROUP:
        return new TopicBoardsGroup(new String(RandomStringUtils.randomAlphabetic(1, 256)), null);
      default:
      case DEFAULT_TOPIC_BOARDS_GROUP:
        return new TopicBoardsGroup(name, null);
    }
  }

}
