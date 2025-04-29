package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeViewpointModelRefDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeUpdateViewpointModelsDto {

    public ArrayList<CdeViewpointModelRefDto> modelReferences;
}
