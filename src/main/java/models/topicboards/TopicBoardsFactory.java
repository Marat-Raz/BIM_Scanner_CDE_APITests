package models.topicboards;

import java.util.UUID;
import models.RandomWord;

public class TopicBoardsFactory {

  private String name = RandomWord.randomAllCharacters(1, 256);
  private String description = RandomWord.randomAllCharacters(1, 100); // Can up to 10000 characters
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
