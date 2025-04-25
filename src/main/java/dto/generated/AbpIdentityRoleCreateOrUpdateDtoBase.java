package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpIdentityRoleCreateOrUpdateDtoBase {

    private Object extraProperties;
    private String name;
    private Boolean isDefault;
    private Boolean isPublic;
}
