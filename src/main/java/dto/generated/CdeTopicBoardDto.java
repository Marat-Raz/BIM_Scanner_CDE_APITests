package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeTopicBoardCustomFieldDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicBoardDto {

    public String type;
    public String id;
    public String projectId;
    public String parentGroupId;
    public String name;
    public String description;
    public ArrayList<CdeTopicBoardCustomFieldDto> customFields;
}
