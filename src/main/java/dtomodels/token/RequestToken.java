package dtomodels.token;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RequestToken { // todo генерировать dto для Foundation API v1.1

  private String grantType;
  private String username;
  private String password;
  private String scope;
  private String clientId;
  private String clientSecret;

  @Override
  public String toString() {
    return "grant_type=" + grantType + "&" +
        "username=" + username + "&" +
        "password=" + password + "&" +
        "scope=" + scope.replaceAll(" ", "+") + "&" +
        "client_id=" + clientId + "&" +
        "client_secret=" + clientSecret;
  }
}
