package projects;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.util.List;

public class FilterSwitcher {

  public static void withTemporaryFilters(Runnable action) {

    List<Filter> originalFilters = RestAssured.filters();
    try {
      RestAssured.filters(
          new RequestLoggingFilter(LogDetail.URI),
          new ResponseLoggingFilter(LogDetail.STATUS),
          new AllureRestAssured()
      );

      action.run();

    } finally {
      RestAssured.replaceFiltersWith(originalFilters);
    }
  }

}
