package topiccomments;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeCreateTopicCommentDto;
import dto.generated.CdeTopicCommentDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел TopicComments(Комментарии к задачам)")
@Story("Обновление(изменение) комментария к задаче")
public class UpdateTopicCreateTopicCommentTests extends CreateTopicCommentsBaseTests {

  private ValidatableResponse updateTopicCommentResponse;

  @Test
  @Tag(value = "smoke")
  @DisplayName("Обновить комментарий к задаче")
  public void updateTopicCommentTest() {
    CdeCreateTopicCommentDto newTopicComment = createTopicCommentDto;
    createTopicCommentDto.setComment("New createTopicCommentDto");
    updateTopicCommentResponse = topicCommentsClient
        .updateTopicComment(topicBoardId, defaultTopicId, topicCommentId, newTopicComment);
    statusCode = extractStatusCode(updateTopicCommentResponse);
    cdeTopicCommentDto = updateTopicCommentResponse.extract().as(CdeTopicCommentDto.class);
    String expectedTopicCommentId = cdeTopicCommentDto.getId();

    assertEquals(SC_OK, statusCode);
    assertEquals(topicCommentId, expectedTopicCommentId);
    assertEquals(createTopicCommentDto.getComment(), cdeTopicCommentDto.getComment());
  }
}
