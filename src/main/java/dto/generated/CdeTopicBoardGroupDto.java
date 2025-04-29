package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicBoardGroupDto {

    public String type;
    public String id;
    public String projectId;
    public String parentGroupId;
    public String name;
}
