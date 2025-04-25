package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicEventDto {

    private String topicId;
    private String creationTime;
    private CdeUserDto author;
    private ArrayList<CdeTopicEventItemDto> events;
}
