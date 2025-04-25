package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpPermissionGroupDto {

    private String name;
    private String displayName;
    private String displayNameKey;
    private String displayNameResource;
    private ArrayList<AbpPermissionGrantInfoDto> permissions;
}
