package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {

  public String userName;
  public String name;
  public String surname;
  public String email;
  public boolean isActive;
  public String creationTime;
  public String id;

}
