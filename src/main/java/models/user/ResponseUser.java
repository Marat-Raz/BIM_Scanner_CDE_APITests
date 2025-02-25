package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseUser {

      String userName;
      String name;
      String surname;
      String email;
      String phoneNumber;
      boolean isActive;
      boolean lockoutEnabled;
      String roleNames;
      String password;

}
