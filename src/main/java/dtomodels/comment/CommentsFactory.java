package dtomodels.comment;

import dtomodels.RandomWord;

public class CommentsFactory {

  public String comment = RandomWord.randomAllCharacters(1, 256);

  public Comment createTopicComment(CommentType commentType) {
    switch (commentType) {
      case NULL_TOPIC_COMMENT:
        return new Comment(null);
      case DEFAULT_TOPIC_COMMENT:
      default:
        return new Comment(comment);
    }
  }
}
