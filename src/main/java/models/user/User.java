package usermodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

  private String name, login, password, email, phoneNumber;
  private boolean isActive;

}
