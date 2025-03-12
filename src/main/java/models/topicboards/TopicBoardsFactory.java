package models.topicboards;

import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;

public class TopicBoardsFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);
  private String description = RandomStringUtils.randomAlphabetic(1, 100); // Can up to 10000 characters
  private String wrongParentGroupId = String.valueOf(UUID.randomUUID());

  public TopicBoards createTopicBoards(TopicBoardsType topicBoardsType) {
    switch (topicBoardsType) {
      case NEW_TOPIC_BOARDS:
        return new TopicBoards(name, "new TopicBoards" + description, null);
      case TOPIC_BOARDS_WITHOUT_NAME:
        return new TopicBoards(null, description, wrongParentGroupId);
      case DEFAULT_TOPIC_BOARDS:
      default:
        return new TopicBoards(name, null, null);
    }
  }

}
