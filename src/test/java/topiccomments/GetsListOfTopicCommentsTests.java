package topiccomments;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import dtomodels.comment.ResponseTopicComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetsListOfTopicCommentsTests extends TopicsCommentsBaseTests {

  private ValidatableResponse getsTopicComments;
  List<ResponseTopicComment> arrayOfTopicComments = new ArrayList<>();

  @Test
  @Tag(value = "smoke")
  @DisplayName("Получить список всех комментариев задач")
  public void getsListOfTopicCommentsTest() {
    getsTopicComments = topicCommentsClient.getTopicComments(topicBoardId, defaultTopicId);
    statusCode = extractStatusCode(getsTopicComments);
    arrayOfTopicComments = Arrays.asList(getsTopicComments.extract()
        .as(ResponseTopicComment[].class));

    assertEquals(SC_OK, statusCode);
    assertEquals(topicCommentId, arrayOfTopicComments.get(0).getId());
  }

}
