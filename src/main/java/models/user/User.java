package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

  private String userName
      , email, password, appName;
/*  private String userName, name, surname, email, phoneNumber;
  private boolean isActive, lockoutEnabled;
  private String[] roleNames;
  private String password;*/

}
