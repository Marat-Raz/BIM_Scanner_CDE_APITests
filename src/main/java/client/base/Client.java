package client.base;

import static io.restassured.http.ContentType.MULTIPART;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {

  public static final String BASE_URL = "https://cde-api.test.briodev.ru/";
  public static final String TOKEN_BASE_URL = "https://cde-auth.test.briodev.ru/";
  public static String DEFAULT_USER_ACCESS_TOKEN;
  public static String ADMIN_ACCESS_TOKEN;

  protected RequestSpecification getBaseSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setBaseUri(BASE_URL)
        .build(); // todo рассмотреть возможность вставки токена тут
  }

  protected RequestSpecification getTokenBaseSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.URLENC.withCharset("UTF-8"))
        .setBaseUri(TOKEN_BASE_URL)
        .build();
  }

  protected RequestSpecification multipartBaseSpec() {
    return new RequestSpecBuilder()
        .setContentType(MULTIPART)
        .setBaseUri(BASE_URL)
        .build();
  }

}
