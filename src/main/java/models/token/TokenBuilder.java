package models.token;

import lombok.Getter;
import lombok.Setter;
import models.user.User;

@Getter
@Setter
public class TokenBuilder {

  static String grantType = "password";
  static String username;
  static String password;
  static String scope = "openid profile CDE email phone";
  static String clientId = "CDE_TestClient";
  static String clientSecret = "a7af4e9397dc457cb99672d3cdc221c0";

  public static RequestToken getTokenForAdminUser() {
    return RequestToken.builder()
        .grantType(grantType)
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
