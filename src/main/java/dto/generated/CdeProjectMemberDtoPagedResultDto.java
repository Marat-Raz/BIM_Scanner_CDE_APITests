package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeProjectMemberDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeProjectMemberDtoPagedResultDto {

    public ArrayList<CdeProjectMemberDto> items;
    public Long totalCount;
}
