package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicBoardDetailsDto {

    private String id;
    private String projectId;
    private String parentGroupId;
    private String name;
    private String description;
    private ArrayList<CdeTopicBoardCustomFieldDetailsDto> customFields;
}
