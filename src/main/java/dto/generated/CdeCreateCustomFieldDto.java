package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeCreateCustomFieldDto {

    private String name;
    private String description;
    private CdeCustomFieldType type;
    private ArrayList<CdeAddEnumerationCustomFieldItemDto> enumerationItems;
}
