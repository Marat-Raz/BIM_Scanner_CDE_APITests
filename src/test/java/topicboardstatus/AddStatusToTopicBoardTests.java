package topicboardstatus;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddStatusToTopicBoardTests extends TopicBoardStatusBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать статус в доске задач дает код ответа 200")
  public void addStatusToTopicBoardTest() {
    statusCode = extractStatusCode(addStatusResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(status.getName(), responseStatuses.getName()),
        () -> assertEquals(status.getColor(), responseStatuses.getColor())
    );
  }
}
