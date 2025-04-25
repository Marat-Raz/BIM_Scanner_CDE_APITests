package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicViewpointDto {
    private String id;
    private Boolean hasSnapshot;
    private ArrayList<CdeViewpointMarkerDto> markers;
    private ArrayList<CdeViewpointModelRefDto> models;
}
