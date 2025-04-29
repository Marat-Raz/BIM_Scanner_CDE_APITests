package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeTopicBoardStatusDto;
import dto.generated.CdeLabelDto;
import dto.generated.CdeCustomFieldValueDto;
import dto.generated.CdeTopicBoardTypeDto;
import dto.generated.CdeTopicModelReferenceDto;
import dto.generated.CdeTopicBoardPriorityDto;
import dto.generated.CdeUserDto;
import dto.generated.CdeTopicViewpointDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicDetailsDto {

    public String id;
    public String serverAssignedId;
    public String creationTime;
    public CdeUserDto creator;
    public String lastModificationTime;
    public CdeUserDto lastModifier;
    public String title;
    public String description;
    public String dueDate;
    public CdeUserDto assignedTo;
    public String topicBoardId;
    public String concurrencyStamp;
    public CdeTopicBoardTypeDto type;
    public CdeTopicBoardStatusDto status;
    public CdeTopicBoardPriorityDto priority;
    public ArrayList<CdeCustomFieldValueDto> customFields;
    public ArrayList<CdeLabelDto> labels;
    public ArrayList<CdeTopicViewpointDto> viewpoints;
    public ArrayList<CdeTopicModelReferenceDto> models;
    public Integer numberOfComments;
}
