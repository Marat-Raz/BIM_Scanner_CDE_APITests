package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicEventDtoPagedResultDto { // todo заменить на PaginatedResponse

    private ArrayList<CdeTopicEventDto> items;
    private Long totalCount;
}
