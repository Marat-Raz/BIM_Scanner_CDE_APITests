package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CdeCustomFieldDto {

    private String id;
    private String creationTime;
    private String creatorId;
    private String type;
    private String name;
    private String description;
    private Boolean archived;
    private ArrayList<CdeEnumerationCustomFieldItemDto> enumerationItems;
}
