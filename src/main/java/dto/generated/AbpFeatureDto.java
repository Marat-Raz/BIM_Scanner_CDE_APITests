package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.AbpFeatureProviderDto;
import dto.generated.AbpIStringValueType;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpFeatureDto {

    public String name;
    public String displayName;
    public String value;
    public AbpFeatureProviderDto provider;
    public String description;
    public AbpIStringValueType valueType;
    public Integer depth;
    public String parentName;
}
