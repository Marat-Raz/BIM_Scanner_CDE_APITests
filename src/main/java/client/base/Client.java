package client.base;

import static org.hamcrest.Matchers.containsString;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Client {

  public static final String BASE_URL = "https://cde-api.test.briodev.ru/";
  public static final String TOKEN_BASE_URL = "https://cde-auth.test.briodev.ru/";

  protected RequestSpecification getBaseSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setBaseUri(BASE_URL)
        .build();
  }

  protected RequestSpecification getTokenBaseSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.URLENC.withCharset("UTF-8"))
        .setBaseUri(TOKEN_BASE_URL)
        .build();
  }

  public ResponseSpecification checkStatusCodeInResponse() {
    return new ResponseSpecBuilder().expectStatusCode(200)
        .expectBody(containsString("Success"))
        .build();
  }

}
