package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeSetCustomFieldValueDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeCreateTopicDto {

    public String title;
    public String description;
    public String dueDate;
    public String assignedToId;
    public String typeId;
    public String statusId;
    public String priorityId;
    public ArrayList<CdeSetCustomFieldValueDto> customFields;
    public ArrayList<String> labels;
}
