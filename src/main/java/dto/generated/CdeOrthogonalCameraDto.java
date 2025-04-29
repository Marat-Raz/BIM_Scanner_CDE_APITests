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
public class CdeOrthogonalCameraDto {

    public CdeVectorDto location;
    public CdeVectorDto direction;
    public CdeVectorDto upVector;
    public String type;
    public Double aspectRatio;
    public Double viewToWorldScale;
}
