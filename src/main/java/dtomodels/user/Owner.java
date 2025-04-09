package dtomodels.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Owner extends User {

  public Owner(
      String userName,
      String name,
      String surname,
      String email,
      String phoneNumber,
      boolean isActive,
      boolean lockoutEnabled,
      String roleNames,
      String password
  ) {
    super(userName, name, surname, email, phoneNumber, isActive,
        lockoutEnabled, new String[]{roleNames}, password);
  }

}
