package models.token;

import lombok.Getter;
import lombok.Setter;
import models.user.User;

@Getter
@Setter
public class TokenBuilder {

  private static String grantType;
  private static String username;
  private static String password;
  private static final String scope = "openid profile CDE email phone";
  private static final String clientId = "CDE_TestClient";
  private static final String clientSecret = "a7af4e9397dc457cb99672d3cdc221c0";

  public static RequestToken getTokenForAdminUser() {
    return RequestToken.builder()
        .grantType("password")
        .username("admin")
        .password("1q2w3E*")
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
