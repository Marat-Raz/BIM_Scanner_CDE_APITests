package models.token;

import lombok.Getter;
import lombok.Setter;
import models.user.User;
import static CommonConstants.ADMIN;
import static CommonConstants.GRANT_TYPE;

@Getter
@Setter
public class TokenBuilder {

  private static String grantType;
  private static String username;
  private static String password;
  private static String scope = "openid profile CDE email phone";
  private static String clientId = "CDE_TestClient";
  private static String clientSecret = "a7af4e9397dc457cb99672d3cdc221c0";

  public static RequestToken getTokenForAdminUser() {
    return RequestToken.builder()
        .grantType(GRANT_TYPE)
        .username(ADMIN)
        .password(CommonConstants.ADMIN)
        .scope(scope)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }

  public static RequestToken getTokenForUser(User user) {
    return RequestToken.builder()
        .grantType(grantType)
        .username(user.getUserName())
        .password(user.getPassword())
        .scope(scope)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }

}
