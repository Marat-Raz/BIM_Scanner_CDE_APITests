package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.CdeVectorDto;
import dto.generated.CdeBitmapType;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeViewpointBitmapDto {

    public CdeBitmapType bitmapType;
    public CdeVectorDto location;
    public CdeVectorDto normal;
    public CdeVectorDto up;
    public Double height;
}
