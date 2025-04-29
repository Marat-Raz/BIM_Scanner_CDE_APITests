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
public class CdeCreateViewpointBitmapDto {

    public CdeVectorDto location;
    public CdeVectorDto normal;
    public CdeVectorDto up;
    public Long height;
    public String bitmapData;
}
