package dto.generated;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeProjectRoleDto {

  private String id;
  private String name;
  private Boolean isDefault;
  private String concurrencyStamp;
  private List<String> permissions;

}
