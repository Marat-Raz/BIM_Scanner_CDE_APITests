package topicboardpriorities;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import models.priorities.ResponsePriorities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddPrioritiesToTopicBoardTests extends TopicBoardPrioritiesBaseTests {


  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать приоритет в доске задач")
  public void addPrioritiesToTopicBoardTest() {
    statusCode = extractStatusCode(addPrioritiesResponse);
    ResponsePriorities responsePriorities = addPrioritiesResponse.extract()
        .as(ResponsePriorities.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(priority.getName(), responsePriorities.getName()),
        () -> assertEquals(priority.getColor(), responsePriorities.getColor())
    );
  }
}
