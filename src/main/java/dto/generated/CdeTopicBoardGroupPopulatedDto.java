package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicBoardGroupPopulatedDto {

    public String type;
    public String id;
    public String projectId;
    public String parentGroupId;
    public String name;
    public ArrayList<Object> items;
}
