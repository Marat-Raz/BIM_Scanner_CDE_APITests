package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeColoringComponentsGroupDto {

    private String color;
    private ArrayList<CdeViewpointComponentDto> components;
}
