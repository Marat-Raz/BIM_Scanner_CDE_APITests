package topiccomments;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddTopicCommentsToTopicTests extends TopicsCommentsBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Добавить комментарий в задачу возвращает код 200")
  public void addTopicCommentsToTopicTest() {
    statusCode = extractStatusCode(addTopicsCommentsResponse);

    assertEquals(SC_OK, statusCode);
    assertEquals(topicComment.getComment(), responseTopicComment.getComment());
  }
}
