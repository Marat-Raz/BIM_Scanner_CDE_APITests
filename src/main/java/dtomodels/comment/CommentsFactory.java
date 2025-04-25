package dtomodels.comment;

import dto.generated.CdeCreateTopicCommentDto;
import org.apache.commons.lang3.RandomStringUtils;

public class CommentsFactory {

  public String comment = RandomStringUtils.randomAlphanumeric(1, 256);

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
