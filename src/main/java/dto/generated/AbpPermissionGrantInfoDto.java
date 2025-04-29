package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpProviderInfoDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpPermissionGrantInfoDto {

    public String name;
    public String displayName;
    public String parentName;
    public Boolean isGranted;
    public ArrayList<String> allowedProviders;
    public ArrayList<AbpProviderInfoDto> grantedProviders;
}
