package projects;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.util.List;

public class FilterSwitcher {

  // Метод для временной замены фильтров
  public static void withTemporaryFilters(Runnable action) {
    // Сохраняем текущие фильтры
    List<Filter> originalFilters = RestAssured.filters();

    try {
      // Устанавливаем новые фильтры
      RestAssured.filters(
          new RequestLoggingFilter(LogDetail.URI),
          new ResponseLoggingFilter(LogDetail.STATUS),
          new AllureRestAssured()
      );

      // Выполняем действие с новыми фильтрами
      action.run();
    } finally {
      // Возвращаем оригинальные фильтры
      RestAssured.replaceFiltersWith(originalFilters);
    }
  }

}
