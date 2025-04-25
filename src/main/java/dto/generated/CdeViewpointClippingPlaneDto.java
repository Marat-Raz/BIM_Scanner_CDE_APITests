package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeViewpointClippingPlaneDto {

    private CdeVectorDto location;
    private CdeVectorDto direction;
}
