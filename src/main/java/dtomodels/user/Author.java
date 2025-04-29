package dtomodels.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Author extends CdeUserDto {

  public Author(String userName, String name, String surname, String email, boolean isActive,
      String creationTime, String id) {
    super(userName, name, surname, email, isActive, creationTime, id);
  }
}
