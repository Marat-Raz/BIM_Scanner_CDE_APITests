package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeAddOrUpdateEnumerationCustomFieldItemDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeUpdateCustomFieldDto {

    public String name;
    public String description;
    public Boolean archived;
    public ArrayList<CdeAddOrUpdateEnumerationCustomFieldItemDto> enumerationItems;
}
