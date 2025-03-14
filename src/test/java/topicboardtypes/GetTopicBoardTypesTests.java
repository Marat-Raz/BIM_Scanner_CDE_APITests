package topicboardtypes;

import static dtomodels.types.TypesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicBoardTypesClient;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import dtomodels.types.Types;
import dtomodels.types.TypesFactory;
import org.junit.jupiter.api.*;

public class GetTopicBoardTypesTests extends TopicBoardTypeBaseTests {

  private static TopicBoardTypesClient topicBoardTypesClient = new TopicBoardTypesClient();
  private ValidatableResponse getAllTypes;
  private static List<Types> expectedTypes = new ArrayList<>();

  @BeforeEach
  public void addTypesToTopicBoards() {
    expectedTypes.add(type);
    for (int i = 0; i < 5; i++) {
      expectedTypes.add(new TypesFactory().createTypes(DEFAULT));
    }
    for (Types type : expectedTypes) {
      addTypesResponse = topicBoardTypesClient.addTopicBoardTypes(topicBoardId, type);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все приоритеты доски задач")
  public void getTopicBoardTypesTest() {
    getAllTypes = topicBoardTypesClient.getTopicBoardTypes(topicBoardId);
    List<Types> actualTypes = Arrays.asList(getAllTypes.extract().as(Types[].class));

    assertAll(
        () -> assertEquals(new HashSet<>(expectedTypes), new HashSet<>(actualTypes))
    );
  }
}
