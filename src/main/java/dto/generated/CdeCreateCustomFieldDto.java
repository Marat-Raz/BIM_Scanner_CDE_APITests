package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeCustomFieldType;
import dto.generated.CdeAddEnumerationCustomFieldItemDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeCreateCustomFieldDto {

    public String name;
    public String description;
    public CdeCustomFieldType type;
    public ArrayList<CdeAddEnumerationCustomFieldItemDto> enumerationItems;
}
