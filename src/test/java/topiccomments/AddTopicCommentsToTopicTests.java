package topiccomments;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicComments(Комментарии к задачам)")
@Story("Добавление комментария к задаче")
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
