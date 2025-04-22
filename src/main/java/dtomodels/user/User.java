package dtomodels.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User extends CdeUserDto {

  public User(String userName, String name, String surname, String email, boolean isActive,
      String creationTime, String id) {
    super(userName, name, surname, email, isActive, creationTime, id);
  }
}
