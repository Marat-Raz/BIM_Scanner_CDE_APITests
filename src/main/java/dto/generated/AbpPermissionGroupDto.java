package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpPermissionGrantInfoDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpPermissionGroupDto {

    public String name;
    public String displayName;
    public String displayNameKey;
    public String displayNameResource;
    public ArrayList<AbpPermissionGrantInfoDto> permissions;
}
