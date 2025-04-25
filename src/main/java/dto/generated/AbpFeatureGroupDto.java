package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpFeatureGroupDto {

    private String name;
    private String displayName;
    private ArrayList<AbpFeatureDto> features;
}
