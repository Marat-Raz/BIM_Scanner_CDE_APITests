package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpTenantDto {

    private Object extraProperties;
    private String id;
    private String name;
    private String concurrencyStamp;
}
