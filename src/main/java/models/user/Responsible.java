package models.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Responsible extends User {

  public Responsible(String userName, String name, String surname, String email, String phoneNumber,
      boolean isActive, boolean lockoutEnabled, String roleNames, String password) {

    super(userName, name, surname, email, phoneNumber, isActive,
        lockoutEnabled, new String[]{roleNames}, password);

  }
}
