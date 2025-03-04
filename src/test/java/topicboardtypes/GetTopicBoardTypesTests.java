package topicboardtypes;

import static models.types.TypesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicBoardTypesClient;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import models.types.Types;
import models.types.TypesFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetTopicBoardTypesTests extends TopicBoardTypeBaseTests {

  private static TopicBoardTypesClient topicBoardTypesClient = new TopicBoardTypesClient();
  private static final int typesCount = 5;
  private ValidatableResponse getAllTypes;
  private static List<Types> expectedTypes = new ArrayList<>();

  @BeforeAll
  public static void addTypesToTopicBoards() {
    for (int i = 0; i < typesCount; i++) {
      expectedTypes.add(new TypesFactory().createTypes(DEFAULT));
    }
    for (Types type : expectedTypes) {
      addTypesResponse = topicBoardTypesClient.addTypesToTopicBoard(topicBoardId, type);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все приоритеты доски задач")
  public void getTopicBoardTypesTest() {
    getAllTypes = topicBoardTypesClient.getTopicBoardTypes(topicBoardId);
    List<Types> actualTypes = Arrays.asList(getAllTypes.extract().as(Types[].class));

    assertAll(
        () -> assertEquals(typesCount, actualTypes.size()),
        () -> assertEquals(new HashSet<>(expectedTypes), new HashSet<>(actualTypes))
    );
  }
}
