package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import dtomodels.comment.Comment;
import dtomodels.models.ModelFileFormat;
import io.qameta.allure.Step;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.MultiPartSpecification;
import java.io.File;
import java.util.Map;

public class ModelRevisionsClient extends Client {

  private final String MODELS = "/models/";
  private final String REVISIONS = "/revisions/";

  @Step("Получить список моделей в проекте с параметрами запроса")
  public ValidatableResponse getListOfModelRevisions(String projectId,
      String modelId, Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + projectId + MODELS + modelId + REVISIONS)
        .then();
  }

  @Step("Получить список моделей в проекте без параметров запроса")
  public ValidatableResponse getListOfModelRevisionsWithoutQueryOptions(String projectId,
      String modelId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MODELS + modelId + REVISIONS)
        .then();
  }

  @Step("Загрузить новый файл модели")
  public ValidatableResponse uploadNewModelFile(
      String projectId,
      String modelId,
      File modelFile,
      Comment comment
  ) {
    MultiPartSpecification commentPart = new MultiPartSpecBuilder(comment.getComment())
        .controlName("comment")
        .mimeType("text/plain")
        .charset("UTF-8")
        .build();
    return given()
        .spec(getMultipartSpecWithUtf8())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .multiPart("file", modelFile)
        .multiPart(commentPart)
        .when()
        .post(API_PROJECTS + projectId + MODELS + modelId + REVISIONS)
        .then();
  }

  @Step("Получить версию модели в проекте по ID")
  public ValidatableResponse getModelRevisionByVersion(String projectId, String modelId,
      int modelVersion) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MODELS + modelId + REVISIONS + modelVersion)
        .then();
  }

  @Step("Изменить комментарий к версии модели")
  public ValidatableResponse changeModelRevisionComment(
      String projectId,
      String modelId,
      int modelVersion,
      Comment comment) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .body(comment)
        .when()
        .put(API_PROJECTS + projectId + MODELS + modelId + REVISIONS + modelVersion)
        .then();
  }

  @Step("Загрузить файл ревизии модели в указанном формате")
  public ValidatableResponse downloadModelRevisionFile(
      String projectId,
      String modelId,
      int version,
      ModelFileFormat format) {

    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .header("Accept", format.getMediaType())
        .when()
        .get(API_PROJECTS + projectId + MODELS + modelId + REVISIONS + version + "/download")
        .then();
  }

  @Step("Получить статус конвертации модели")
  public ValidatableResponse getModelConversionStatus(String projectId, String modelId,
      int version) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + projectId + MODELS + modelId + REVISIONS + version + "/status")
        .then();
  }
}