package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeViewpointModelRefDto;
import dto.generated.CdeViewpointMarkerDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicViewpointDto {

    public String id;
    public Boolean hasSnapshot;
    public ArrayList<CdeViewpointMarkerDto> markers;
    public ArrayList<CdeViewpointModelRefDto> models;
}
