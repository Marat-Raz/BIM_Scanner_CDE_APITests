package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicModelElementsDto {

    private String modelId;
    private Integer modelVersion;
    private String modelName;
    private ArrayList<CdeTopicElementReferenceDto> elements;
}
