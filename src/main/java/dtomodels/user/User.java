package dtomodels.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

  protected String userName;
  protected String name;
  protected String surname;
  protected String email;
  protected String phoneNumber;
  protected boolean isActive;
  protected boolean lockoutEnabled;
  protected String[] roleNames;
  protected String password;

}
