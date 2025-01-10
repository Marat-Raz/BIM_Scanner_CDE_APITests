package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

  private String userName;
  private String email;
  private String password;
  private String appName;

}
