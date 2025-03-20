import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import client.BuildInfoClient;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import models.BuildInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class getBuildInfoTests {

  ValidatableResponse getResponse;
  BuildInfoClient buildInfoClient = new BuildInfoClient();

  // todo решить Вопрос:оставить эти тесты независимыми от всех или сделать частью остальных?

  @BeforeAll
  @Step("Запуск Allure и логирования запросов по API")
  public static void globalSetUp() {
    RestAssured.filters(
        new RequestLoggingFilter(), new ResponseLoggingFilter(),
        new AllureRestAssured());
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на получение информации о версии приложения дает ответ 200")
  public void getBuildInfoTest() {
    getResponse = buildInfoClient.getBuildInfo();
    int statusCode = getResponse.extract().statusCode();
    BuildInfo buildInfo = getResponse.extract().as(BuildInfo.class);

    assertEquals(SC_OK, statusCode);
    assertNotNull(buildInfo);
  }

  @Test
  @Tag(value = "smoke")
  @DisplayName("Запрос на получение информации о версии приложения дает ответ 200")
  public void getBuildInfoSemverTest() {
    getResponse = buildInfoClient.getBuildInfoSemver();
    int statusCode = getResponse.extract().statusCode();
    String versionTxt = getResponse.extract().body().asString();

    assertEquals(SC_OK, statusCode);
    assertNotNull(versionTxt);
  }

}
