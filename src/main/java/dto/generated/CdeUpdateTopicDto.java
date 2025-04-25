package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeUpdateTopicDto {

    private String title;
    private String description;
    private String dueDate;
    private String assignedToId;
    private String concurrencyStamp;
    private String typeId;
    private String statusId;
    private String priorityId;
    private ArrayList<CdeSetCustomFieldValueDto> customFields;
    private ArrayList<String> labels;
}
