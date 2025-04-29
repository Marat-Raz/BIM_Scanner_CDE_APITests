package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpIdentityRoleDto {

    public String id;
    public String name;
    public Boolean isDefault;
    public Boolean isStatic;
    public Boolean isPublic;
    public String concurrencyStamp;
}
