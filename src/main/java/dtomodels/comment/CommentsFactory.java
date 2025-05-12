package dtomodels.comment;

import dto.generated.CdeCreateTopicCommentDto;
import dtomodels.RandomWord;

public class CommentsFactory {

  public String comment = RandomWord.randomLatinAndNumberCharacters(1, 256);

  public CdeCreateTopicCommentDto createTopicComment(CommentType commentType) {
    switch (commentType) {
      case NULL_TOPIC_COMMENT:
        return new CdeCreateTopicCommentDto(null);
      case DEFAULT_TOPIC_COMMENT:
      default:
        return new CdeCreateTopicCommentDto(comment);
    }
  }
}
