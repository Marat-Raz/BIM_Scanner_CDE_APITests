package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeCreateViewpointDto {
    private Object camera;
    private CdeCreateSnapshotDto snapshot;
    private ArrayList<CdeViewpointModelRefDto> modelReferences;
    private ArrayList<CdeViewpointMarkerDto> markers;
    private ArrayList<CdeViewpointClippingPlaneDto> clippingPlanes;
    private CdeCreateComponentsDto components;
    private ArrayList<CdeCreateViewpointBitmapDto> bitmaps;
    private ArrayList<CdeViewpointLineDto> lines;
}
