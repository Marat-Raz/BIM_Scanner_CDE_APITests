package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpPermissionGroupDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpGetPermissionListResultDto {

    public String entityDisplayName;
    public ArrayList<AbpPermissionGroupDto> groups;
}
