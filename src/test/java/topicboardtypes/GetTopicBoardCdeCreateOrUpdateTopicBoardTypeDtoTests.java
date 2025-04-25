package topicboardtypes;

import static dtomodels.types.TypesType.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.TopicBoardTypesClient;
import dto.generated.CdeCreateOrUpdateTopicBoardTypeDto;
import dtomodels.types.TypesFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardType(«Типы» в досках задач)")
@Story("Получение «Типов» с доски задач")
public class GetTopicBoardCdeCreateOrUpdateTopicBoardTypeDtoTests extends TopicBoardTypeBaseTests {

  private static TopicBoardTypesClient topicBoardTypesClient = new TopicBoardTypesClient();
  private ValidatableResponse getAllTypes;
  private static List<CdeCreateOrUpdateTopicBoardTypeDto> expectedTypes = new ArrayList<>();

  @BeforeEach
  public void addTypesToTopicBoards() {
    expectedTypes.add(type);
    for (int i = 0; i < 5; i++) {
      expectedTypes.add(new TypesFactory().createTypes(DEFAULT));
    }
    for (CdeCreateOrUpdateTopicBoardTypeDto type : expectedTypes) {
      addTypesResponse = topicBoardTypesClient.addTopicBoardTypes(topicBoardId, type);
    }
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить все приоритеты доски задач")
  public void getTopicBoardTypesTest() {
    getAllTypes = topicBoardTypesClient.getTopicBoardTypes(topicBoardId);
    List<CdeCreateOrUpdateTopicBoardTypeDto> actualTypes = Arrays.asList(getAllTypes.extract().as(
        CdeCreateOrUpdateTopicBoardTypeDto[].class));

    assertAll(
        () -> assertEquals(new HashSet<>(expectedTypes), new HashSet<>(actualTypes))
    );
  }
}
