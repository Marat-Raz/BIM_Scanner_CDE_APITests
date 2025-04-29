package dtomodels.projectroles;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseProjectRole {

  private String id;
  private String name;
  private Boolean isDefault;
  private String concurrencyStamp;
  private List<String> permissions;

}
