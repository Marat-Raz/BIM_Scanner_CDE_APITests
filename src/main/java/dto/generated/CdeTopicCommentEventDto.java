package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeTopicCommentEventItemDto;
import dto.generated.CdeUserDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicCommentEventDto {

    public String commentId;
    public String topicId;
    public String creationTime;
    public CdeUserDto author;
    public ArrayList<CdeTopicCommentEventItemDto> events;
}
