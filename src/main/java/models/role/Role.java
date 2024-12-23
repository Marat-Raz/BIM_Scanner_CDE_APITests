package models.role;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Role {

  private String name;
  private boolean isDefault, isStatic, isPublic;
  private String concurrencyStamp, id;
  private ArrayList extraProperties;

}
