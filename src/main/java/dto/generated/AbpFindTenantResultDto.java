package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpFindTenantResultDto {

    private Boolean success;
    private String tenantId;
    private String name;
    private String normalizedName;
    private Boolean isActive;
}
