package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpIdentityRoleDto {

  private Object extraProperties;
  private String id;
  private String name;
  private Boolean isDefault;
  private Boolean isStatic;
  private Boolean isPublic;
  private String concurrencyStamp;

}
