package dto.generated;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeUpdateProjectRoleDto {

  private String name;
  private Boolean isDefault;
  private String concurrencyStamp;
  private List<String> permissions;

  public static CdeUpdateProjectRoleDto from(CdeProjectRoleDto cdeProjectRoleDto) {
    return new CdeUpdateProjectRoleDto(
        cdeProjectRoleDto.getName(),
        cdeProjectRoleDto.getIsDefault(),
        cdeProjectRoleDto.getConcurrencyStamp(),
        cdeProjectRoleDto.getPermissions()
    );}
}
