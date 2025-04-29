package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpTenantCreateDto {

    public String name;
    public String adminEmailAddress;
    public String adminPassword;
}
