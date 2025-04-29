package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpIdentityRoleCreateDto {

    public String name;
    public Boolean isDefault;
    public Boolean isPublic;
}
