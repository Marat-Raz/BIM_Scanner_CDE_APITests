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
public class CdeSetTopicModelElementsDto {

    public String modelId;
    public Integer modelVersion;
    public ArrayList<CdeTopicElementReferenceDto> elements;
}
