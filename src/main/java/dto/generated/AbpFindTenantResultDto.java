package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpFindTenantResultDto {

    public Boolean success;
    public String tenantId;
    public String name;
    public String normalizedName;
    public Boolean isActive;
}
