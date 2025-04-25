package topicboardtypes;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardType(«Типы» в досках задач)")
@Story("Удаление «Типов» с доски задач")
public class DeleteCdeCreateOrUpdateTopicBoardTypeDtoFromTopicBoardTests extends TopicBoardTypeBaseTests {

  private static ValidatableResponse deleteTypesResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Удалить тип задачи из доски задач")
  public void deleteTypesInTopicBoardTest() {
    deleteTypesResponse = topicBoardTypesClient.deleteTopicBoardTypes(topicBoardId, typeId);
    statusCode = extractStatusCode(deleteTypesResponse);

    assertEquals(SC_NO_CONTENT, statusCode);
  }

}
