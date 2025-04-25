package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeViewpointDto {

    private String id;
    private String creationTime;
    private String creatorId;
    private Object camera;
    private CdeSnapshotDto snapshot;
    private ArrayList<CdeViewpointModelRefDto> modelReferences;
    private ArrayList<CdeViewpointMarkerDto> markers;
    private ArrayList<CdeViewpointBitmapDto> bitmaps;
    private ArrayList<CdeViewpointLineDto> lines;
    private ArrayList<CdeViewpointClippingPlaneDto> clippingPlanes;
}
