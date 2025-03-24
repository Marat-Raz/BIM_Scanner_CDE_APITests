package labels;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddLabelTests extends LabelBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("При создании метки в проекте, в ответе код 200")
  public void addLabelToProjectTest() {
    statusCode = extractStatusCode(baseAddResponse);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(label.getName(), responseLabel.getName()),
        () -> assertEquals(label.getColor(), responseLabel.getColor()),
        () -> assertNotNull(responseLabel.getId())
    );
  }

}
