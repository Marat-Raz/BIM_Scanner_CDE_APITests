package dtomodels.topiccomments;

import dtomodels.RandomWord;

public class TopicCommentsFactory {

  public String comment = RandomWord.randomAllCharacters(1, 256);

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
