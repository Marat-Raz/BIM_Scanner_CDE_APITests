package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpIdentityRoleDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpIdentityRoleDtoPagedResultDto {

    public ArrayList<AbpIdentityRoleDto> items;
    public Long totalCount;
}
