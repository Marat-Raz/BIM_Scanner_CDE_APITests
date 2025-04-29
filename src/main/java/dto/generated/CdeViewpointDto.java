package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeViewpointLineDto;
import dto.generated.CdeViewpointClippingPlaneDto;
import dto.generated.CdeViewpointModelRefDto;
import dto.generated.CdeViewpointMarkerDto;
import dto.generated.CdeViewpointBitmapDto;
import dto.generated.CdeSnapshotDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeViewpointDto {

    public String id;
    public String creationTime;
    public String creatorId;
    public Object camera;
    public CdeSnapshotDto snapshot;
    public ArrayList<CdeViewpointModelRefDto> modelReferences;
    public ArrayList<CdeViewpointMarkerDto> markers;
    public ArrayList<CdeViewpointBitmapDto> bitmaps;
    public ArrayList<CdeViewpointLineDto> lines;
    public ArrayList<CdeViewpointClippingPlaneDto> clippingPlanes;
}
