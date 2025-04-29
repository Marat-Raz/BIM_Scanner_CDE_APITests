package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpUpdateFeatureDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpUpdateFeaturesDto {

    public ArrayList<AbpUpdateFeatureDto> features;
}
