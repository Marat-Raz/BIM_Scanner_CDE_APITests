package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeCameraDto {

    private CdeVectorDto location;
    private CdeVectorDto direction;
    private CdeVectorDto upVector;
    private String type;
}
