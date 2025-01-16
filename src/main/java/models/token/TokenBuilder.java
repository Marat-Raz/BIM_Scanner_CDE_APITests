package models.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenBuilder {

  static String grantType;
  static String username;
  static String password;
  static String scope = "openid profile CDE email phone";
  static String clientId = "CDE_TestClient";
  static String clientSecret = "a7af4e9397dc457cb99672d3cdc221c0";

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

  public static RequestToken getTokenForUser() {
    return RequestToken.builder()
        .grantType(grantType)
        .username(username)
        .password(password)
        .scope(scope)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }

}
