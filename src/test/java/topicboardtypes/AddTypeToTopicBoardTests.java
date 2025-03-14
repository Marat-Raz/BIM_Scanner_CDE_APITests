package topicboardtypes;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import models.types.ResponseTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddTypeToTopicBoardTests extends TopicBoardTypeBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать тип задач в доске задач дает код ответа 200")
  public void addTypesToTopicBoardTest() {
    statusCode = extractStatusCode(addTypesResponse);
    ResponseTypes responseTypes = addTypesResponse.extract().as(ResponseTypes.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(type.getName(), responseTypes.getName()),
        () -> assertEquals(type.getColor(), responseTypes.getColor())
    );
  }
}

