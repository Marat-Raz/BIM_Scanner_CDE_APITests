package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpPermissionGrantInfoDto {

    private String name;
    private String displayName;
    private String parentName;
    private Boolean isGranted;
    private ArrayList<String> allowedProviders;
    private ArrayList<AbpProviderInfoDto> grantedProviders;
}
