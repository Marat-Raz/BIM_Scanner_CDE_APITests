package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeTopicDetailsDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicDetailsDtoPagedResultDto {

    public ArrayList<CdeTopicDetailsDto> items;
    public Long totalCount;
}
