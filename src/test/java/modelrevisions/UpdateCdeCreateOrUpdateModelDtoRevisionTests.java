package modelrevisions;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.generated.CdeCreateTopicCommentDto;
import dtomodels.models.modelrevisions.ResponseModelRevisions;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdateModelRevisionTests extends ModelRevisionsBaseTests {

  private ValidatableResponse updateModelRevisionResponse;

  @Test
  @Tag(value = "positive")
  @DisplayName("Изменить ревизию модели - код ответа 200")
  public void updateModelRevisionTest() {
    int modelVersion = 2;
    CdeCreateTopicCommentDto createTopicCommentDto = new CdeCreateTopicCommentDto("Changed createTopicCommentDto");
    updateModelRevisionResponse = modelRevisionsClient.changeModelRevisionComment(
        projectId,
        modelId,
        modelVersion,
        createTopicCommentDto
    );
    statusCode = extractStatusCode(updateModelRevisionResponse);
    ResponseModelRevisions modelRevisions = updateModelRevisionResponse.extract()
        .as(ResponseModelRevisions.class);

    assertEquals(SC_OK, statusCode);
    assertEquals(createTopicCommentDto.getComment(), modelRevisions.getComment());
  }
}
