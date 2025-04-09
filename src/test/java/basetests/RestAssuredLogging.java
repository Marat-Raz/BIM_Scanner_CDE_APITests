package basetests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RestAssuredLogging  {

  private static final List<Filter> MINIMAL_FILTERS = Arrays.asList(
      new RequestLoggingFilter(LogDetail.URI),
      new ResponseLoggingFilter(LogDetail.STATUS),
      new AllureRestAssured()
  );
  private static final ThreadLocal<List<Filter>> originalFilters = new ThreadLocal<>();

  @Step("Оставляем минимум логов «RestAssured» при запросах с файлами")
  public static void setupMinimalLogging() {
    originalFilters.set(new ArrayList<>(RestAssured.filters()));
    RestAssured.replaceFiltersWith(MINIMAL_FILTERS);
  }

  @Step("Восстанавливаем логирование «RestAssured»")
  public static void restoreOriginalFilters() {
    if (originalFilters.get() != null) {
      RestAssured.replaceFiltersWith(originalFilters.get());
      originalFilters.remove();
    }
  }

  private RestAssuredLogging() {
  }

}
