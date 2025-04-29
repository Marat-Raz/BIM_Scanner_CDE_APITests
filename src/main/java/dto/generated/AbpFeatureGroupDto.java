package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpFeatureDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpFeatureGroupDto {

    public String name;
    public String displayName;
    public ArrayList<AbpFeatureDto> features;
}
