package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

  private String userName, emailAddress, password, appName;
  //private boolean isActive;

}
