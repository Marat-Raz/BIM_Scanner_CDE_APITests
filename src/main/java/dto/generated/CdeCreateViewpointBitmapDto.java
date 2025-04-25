package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeCreateViewpointBitmapDto {
    private CdeVectorDto location;
    private CdeVectorDto normal;
    private CdeVectorDto up;
    private Long height;
    private String bitmapData;
}
