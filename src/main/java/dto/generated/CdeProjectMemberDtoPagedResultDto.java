package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeProjectMemberDtoPagedResultDto {

    private ArrayList<CdeProjectMemberDto> items;
    private Long totalCount;
}
