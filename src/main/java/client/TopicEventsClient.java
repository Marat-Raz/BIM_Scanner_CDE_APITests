package client;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TopicEventsClient extends Client {

  private static final String TOPIC_EVENTS = "api/issues/boards/";

  @Step("Получить события изменений задач в доске задач")
  public ValidatableResponse getTopicEvents(String topicBoardId, Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(TOPIC_EVENTS + topicBoardId + "/topics/events/")
        .then();
  }

  @Step("Получить события изменений для конкретной задачи в доске задач")
  public ValidatableResponse getTopicEventsForSpecificTopic(String topicBoardId, String topicId,
      Map<String, Object> queryParams) {
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .queryParams(queryParams)
        .when()
        .get(TOPIC_EVENTS + topicBoardId + "/topics/" + topicId + "/events/")
        .then();
  }

}