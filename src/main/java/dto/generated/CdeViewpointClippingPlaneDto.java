package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.CdeVectorDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeViewpointClippingPlaneDto {

    public CdeVectorDto location;
    public CdeVectorDto direction;
}
