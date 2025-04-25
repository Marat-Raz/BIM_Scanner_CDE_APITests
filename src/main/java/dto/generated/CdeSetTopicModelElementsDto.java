package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeSetTopicModelElementsDto {

    private String modelId;
    private Integer modelVersion;
    private ArrayList<CdeTopicElementReferenceDto> elements;
}
