package client;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TopicEventsClient extends Client {

  private static final String TOPIC_EVENTS = "/topics/events/";

  @Step("Получить уведомления обо всех изменениях задач в доске задач")
  public ValidatableResponse getTopicEventsWithoutQueryParams(String topicBoardId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + topicBoardId + TOPIC_EVENTS)
        .then();
  }

  @Step("Получить уведомления обо всех изменениях задач в доске задач")
  public ValidatableResponse getTopicEvents(String topicBoardId, Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + topicBoardId + TOPIC_EVENTS)
        .then();
  }

  @Step("Получить список уведомлений об изменениях определенной задачи, "
      + "сгруппированных по дате")
  public ValidatableResponse getTopicEventsForSpecificTopicWithoutQueryParams(String topicBoardId,
      String topicId) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(API_PROJECTS + topicBoardId + "/topics/" + topicId + "/events/")
        .then();
  }

  @Step("Получить список уведомлений об изменениях определенной задачи, "
      + "сгруппированных по дате")
  public ValidatableResponse getTopicEventsForSpecificTopic(String topicBoardId, String topicId,
      Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(API_PROJECTS + topicBoardId + "/topics/" + topicId + "/events/")
        .then();
  }

}