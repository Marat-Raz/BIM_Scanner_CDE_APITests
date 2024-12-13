import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.account.UserClient;
import client.token.TokenClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.ValidatableResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import models.token.TokenBuilder;
import org.junit.jupiter.api.Test;
import models.user.UserGenerator;

public class FirstTest extends StartTests {

  int statusCode;
  boolean isSuccess;
  String userId;
  TokenClient tokenClient = new TokenClient();

  @Test
  public void userSuccessCreateTest() {
    user = UserGenerator.getUser();
    userClient = new UserClient();
    ValidatableResponse response = userClient.createUser(user);
    ValidatableResponse responseAdminToken = tokenClient.createToken(TokenBuilder.getTokenForAdminUser());
    String bearer = responseAdminToken.extract().path("access_token");
//    response.extract().body().as((Type) user);
    user.getUserName();
    userId = response.extract().path("id");
    statusCode = response.extract().statusCode();
    System.out.println(statusCode);
    System.out.println(userId);
    System.out.println(user.getUserName());
    System.out.println(user.toString());
    System.out.println(userClient.checkStatusCodeInResponse());
    System.out.println(bearer);

//    int cod = HttpStatus.SC_UNAUTHORIZED;
/*    accessToken = response.extract().path("accessToken");
    userClient.deleteUser(accessToken);

    isSuccess = response.extract().path("success");
    accessToken = response.extract().path("accessToken");
    String refreshToken = response.extract().path("refreshToken");

    assertEquals(SC_OK, statusCode);
    assertTrue(isSuccess);
    assertNotNull(accessToken);
    assertNotNull(refreshToken);*/
  }


/*  ResponseSpecification responseSpec = new ResponseSpecBuilder()
      .expectStatusCode(200)
      .expectBody(containsString("success"))
      .build();*/
@Test
  public void test1() throws UnsupportedEncodingException {
  var token = TokenBuilder.getTokenForAdminUser();
  System.out.println(token.toString());
/*  Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
  Object o = new JSONParser().parse(gson);*/

  //String json = new GsonBuilder().setPrettyPrinting().create().toJson(token);
//  String newJson = json.replaceAll();

/*  var s = URLEncoder.encode(json.replaceAll("[{}\"\n ]", ""), StandardCharsets.UTF_8.toString());
  String s1 = s.replaceAll("%3A", "=").replaceAll("%2C", "&").replace("openidprofileCDEemailphone", "openid profile CDE email phone");

  System.out.println(json.replaceAll("\"", ""));
  System.out.println(s1);

  ValidatableResponse responseAdminToken = tokenClient.createToken(s1);
  String bearer = responseAdminToken.extract().path("access_token");
  System.out.println(bearer);*/
}
}
