package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpCurrentTenantDto {

    private String id;
    private String name;
    private Boolean isAvailable;
}
