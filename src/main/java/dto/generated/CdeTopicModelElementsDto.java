package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeTopicElementReferenceDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicModelElementsDto {

    public String modelId;
    public Integer modelVersion;
    public String modelName;
    public ArrayList<CdeTopicElementReferenceDto> elements;
}
