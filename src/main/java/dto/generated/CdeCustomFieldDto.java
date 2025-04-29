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
public class CdeCustomFieldDto {

    public String id;
    public String creationTime;
    public String creatorId;
    public String type;
    public String name;
    public String description;
    public Boolean archived;
    public ArrayList<CdeEnumerationCustomFieldItemDto> enumerationItems;
}
