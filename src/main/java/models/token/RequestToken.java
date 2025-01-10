package models.token;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RequestToken {

  private String grant_type;
  private String username;
  private String password;
  private String scope;
  private String client_id;
  private String client_secret;

  @Override
  public String toString() {
    return "grant_type=" + grant_type + "&" +
        "username=" + username + "&" +
        "password=" + password + "&" +
        "scope=" + scope.replaceAll(" ", "+") + "&" +
        "client_id=" + client_id + "&" +
        "client_secret=" + client_secret;
  }
}
