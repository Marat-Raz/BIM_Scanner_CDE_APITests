package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeTopicBoardCustomFieldDetailsDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicBoardDetailsDto {

    public String id;
    public String projectId;
    public String parentGroupId;
    public String name;
    public String description;
    public ArrayList<CdeTopicBoardCustomFieldDetailsDto> customFields;
}
