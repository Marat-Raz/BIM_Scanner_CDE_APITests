package dtomodels.topiccomments;

import org.apache.commons.lang3.RandomStringUtils;

public class TopicCommentsFactory {

  public String comment = RandomStringUtils.randomAlphanumeric(1, 256);

  public TopicComment createTopicComment(TopicCommentType topicCommentType) {
    switch (topicCommentType) {
      case NULL_TOPIC_COMMENT:
        return new TopicComment(null);
      case DEFAULT_TOPIC_COMMENT:
      default:
        return new TopicComment(comment);
    }
  }
}
