package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeEnumerationCustomFieldItemDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicBoardCustomFieldDetailsDto {

    public String id;
    public Boolean isEnabled;
    public Boolean isRequired;
    public String defaultValue;
    public String type;
    public String name;
    public String description;
    public ArrayList<CdeEnumerationCustomFieldItemDto> enumerationItems;
}
