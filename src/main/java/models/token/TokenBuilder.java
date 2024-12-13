package models.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenGenerator {
  static String grant_type;
  static String username;
  static String password;
  static String scope = "openid profile CDE email phone";
  static String client_id = "CDE_TestClient";
  static String client_secret = "a7af4e9397dc457cb99672d3cdc221c0";

  public static RequestToken getTokenForAdminUser() {
    return new RequestToken
        ("password", "admin", "1q2w3E*", scope, client_id, client_secret);
  }

  public static RequestToken getTokenForUser() {
    return new RequestToken
        (grant_type, username, password, scope, client_id, client_secret);
  }

}
