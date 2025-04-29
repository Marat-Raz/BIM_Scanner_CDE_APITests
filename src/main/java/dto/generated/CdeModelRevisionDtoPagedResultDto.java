package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeModelRevisionDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeModelRevisionDtoPagedResultDto {

    public ArrayList<CdeModelRevisionDto> items;
    public Long totalCount;
}
