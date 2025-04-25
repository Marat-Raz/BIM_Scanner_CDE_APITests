package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpTenantCreateDto {

    private String adminEmailAddress;
    private String adminPassword;
}
