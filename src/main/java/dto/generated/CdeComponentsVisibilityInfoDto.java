package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeViewpointComponentDto;
import dto.generated.CdeViewSetupHintsDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeComponentsVisibilityInfoDto {

    public Boolean defaultVisibility;
    public ArrayList<CdeViewpointComponentDto> exceptions;
    public CdeViewSetupHintsDto viewSetupHints;
}
