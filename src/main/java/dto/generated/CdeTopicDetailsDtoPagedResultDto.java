package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicDetailsDtoPagedResultDto {

    private ArrayList<CdeTopicDetailsDto> items;
    private Long totalCount;
}
