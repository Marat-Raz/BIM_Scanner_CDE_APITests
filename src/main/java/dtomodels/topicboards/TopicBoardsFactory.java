package dtomodels.topicboards;

import dto.generated.CdeCreateTopicBoardDto;
import java.util.UUID;
import dtomodels.RandomWord;

public class TopicBoardsFactory {

  private String name = RandomWord.randomAllCharacters(1, 256);
  private String description = RandomWord.randomAllCharacters(1, 100); // Can up to 10000 characters
  private String wrongParentGroupId = String.valueOf(UUID.randomUUID());

  public CdeCreateTopicBoardDto createTopicBoards(TopicBoardsType topicBoardsType) {
    switch (topicBoardsType) {
      case NEW_TOPIC_BOARDS:
        return new CdeCreateTopicBoardDto(name, "new TopicBoards" + description, null);
      case TOPIC_BOARDS_WITHOUT_NAME:
        return new CdeCreateTopicBoardDto(null, description, wrongParentGroupId);
      case DEFAULT_TOPIC_BOARDS:
      default:
        return new CdeCreateTopicBoardDto(name, null, null);
    }
  }

}
