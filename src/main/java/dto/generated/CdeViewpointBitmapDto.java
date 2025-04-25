package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeViewpointBitmapDto {

    private CdeBitmapType bitmapType;
    private CdeVectorDto location;
    private CdeVectorDto normal;
    private CdeVectorDto up;
    private Double height;
}
