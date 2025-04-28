package topiccomments;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeTopicCommentDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicComments(Комментарии к задачам)")
@Story("Получение всех комментариев к задаче")
public class GetsListOfTopicCommentsTests extends CreateTopicCommentsBaseTests {

  private ValidatableResponse getsTopicComments;
  List<CdeTopicCommentDto> arrayOfTopicComments = new ArrayList<>();

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список всех комментариев задач")
  public void getsListOfTopicCommentsTest() {
    getsTopicComments = topicCommentsClient.getTopicComments(topicBoardId, defaultTopicId);
    statusCode = extractStatusCode(getsTopicComments);
    arrayOfTopicComments = Arrays.asList(getsTopicComments.extract()
        .as(CdeTopicCommentDto[].class));

    assertEquals(SC_OK, statusCode);
    assertEquals(topicCommentId, arrayOfTopicComments.get(0).getId());
  }

}
