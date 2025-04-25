package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpFeatureDto {

    private String name;
    private String displayName;
    private String value;
    private AbpFeatureProviderDto provider;
    private String description;
    private AbpIStringValueType valueType;
    private Integer depth;
    private String parentName;
}
