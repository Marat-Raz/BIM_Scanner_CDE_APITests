package dtomodels.role;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Role {

  private String name;
  private boolean isDefault;
  private boolean isStatic;
  private boolean isPublic;
  private String concurrencyStamp;
  private String id;
  private ArrayList extraProperties;

}
