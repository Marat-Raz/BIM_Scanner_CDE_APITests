package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeViewpointLineDto;
import dto.generated.CdeViewpointClippingPlaneDto;
import dto.generated.CdeViewpointModelRefDto;
import dto.generated.CdeCreateViewpointBitmapDto;
import dto.generated.CdeViewpointMarkerDto;
import dto.generated.CdeCreateSnapshotDto;
import dto.generated.CdeCreateComponentsDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeCreateViewpointDto {

    public Object camera;
    public CdeCreateSnapshotDto snapshot;
    public ArrayList<CdeViewpointModelRefDto> modelReferences;
    public ArrayList<CdeViewpointMarkerDto> markers;
    public ArrayList<CdeViewpointClippingPlaneDto> clippingPlanes;
    public CdeCreateComponentsDto components;
    public ArrayList<CdeCreateViewpointBitmapDto> bitmaps;
    public ArrayList<CdeViewpointLineDto> lines;
}
