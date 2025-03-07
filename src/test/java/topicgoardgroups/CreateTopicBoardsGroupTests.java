package topicgoardgroups;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateTopicBoardsGroupTests extends TopicBoardGroupBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("При создании в проекте группы досок задач код ответа 200")
  public void createTopicBoardsGroupTest() {
    statusCode = extractStatusCode(createTopicBoardsGroupResponse);
    // fixme statusCode при появлении новых тестов будет неверным

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals("TopicBoardGroup", responseTopicBoardGroup.type),
        () -> assertEquals(topicBoardsGroup.getName(), responseTopicBoardGroup.name),
        () -> assertEquals(projectId, responseTopicBoardGroup.projectId)
    );
  }

  // todo реализовать тесты из TestIT

}
