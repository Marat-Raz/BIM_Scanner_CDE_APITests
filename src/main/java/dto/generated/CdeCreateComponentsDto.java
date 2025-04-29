package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeComponentsVisibilityInfoDto;
import dto.generated.CdeViewpointComponentDto;
import dto.generated.CdeColoringComponentsGroupDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeCreateComponentsDto {

    public ArrayList<CdeViewpointComponentDto> selection;
    public ArrayList<CdeColoringComponentsGroupDto> coloring;
    public CdeComponentsVisibilityInfoDto visibility;
}
