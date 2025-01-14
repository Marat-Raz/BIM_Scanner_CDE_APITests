package models.token;

import lombok.Getter;
import lombok.Setter;
import models.user.User;

@Getter
@Setter
public class TokenBuilder {

  static String grant_type = "password";
  static String username;
  static String password;
  static String scope = "openid profile CDE email phone";
  static String client_id = "CDE_TestClient";
  static String client_secret = "a7af4e9397dc457cb99672d3cdc221c0";

  public static RequestToken getTokenForAdminUser() {
    return RequestToken.builder()
        .grant_type(grant_type)
        .username("admin")
        .password("1q2w3E*")
        .scope(scope)
        .client_id(client_id)
        .client_secret(client_secret)
        .build();
  }

  public static RequestToken getTokenForUser(User user) {
    return RequestToken.builder()
        .grant_type(grant_type)
        .username(user.getUserName())
        .password(user.getPassword())
        .scope(scope)
        .client_id(client_id)
        .client_secret(client_secret)
        .build();
  }

}
