package topicboardtypes;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeTopicBoardTypeDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicBoardType(«Типы» в досках задач)")
@Story("Добавление «Типов» в доску задач")
public class AddTypeToTopicBoardTests extends TopicBoardTypeBaseTests {

  @Test
  @Tag(value = "smoke")
  @DisplayName("Создать тип задач в доске задач дает код ответа 200")
  public void addTypesToTopicBoardTest() {
    statusCode = extractStatusCode(addTypesResponse);
    CdeTopicBoardTypeDto cdeTopicBoardTypeDto = addTypesResponse.extract().as(CdeTopicBoardTypeDto.class);

    assertEquals(SC_OK, statusCode);
    assertAll(
        () -> assertEquals(type.getName(), cdeTopicBoardTypeDto.getName()),
        () -> assertEquals(type.getColor(), cdeTopicBoardTypeDto.getColor())
    );
  }
}

