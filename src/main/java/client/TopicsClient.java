package client;

import static io.restassured.RestAssured.given;

import client.base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class TopicsClient extends Client {

  private final String TOPICS = "api/issues/boards/";

  @Step("")
  public ValidatableResponse getListOfTopicsFromTopicBoard(String topicBoardId, String filter,
      String search, String orderBy, String top, String skip) {
    // todo необходимо сформировать список опций/фильтров для этого запроса
    return given()
        .spec(getBaseSpec())
        .auth().oauth2(ADMIN_ACCESS_TOKEN)
        .when()
        .get(TOPICS + topicBoardId + "/topics" + filter + search + orderBy + top + skip)
        .then();
  }


}
