package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicBoardCustomFieldDetailsDto {

    private String id;
    private Boolean isEnabled;
    private Boolean isRequired;
    private String defaultValue;
    private String type;
    private String name;
    private String description;
    private ArrayList<CdeEnumerationCustomFieldItemDto> enumerationItems;
}
