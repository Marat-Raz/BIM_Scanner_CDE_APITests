package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpFeatureGroupDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpGetFeatureListResultDto {

    public ArrayList<AbpFeatureGroupDto> groups;
}
