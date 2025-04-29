package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeModelDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeModelDtoPagedResultDto {

    public ArrayList<CdeModelDto> items;
    public Long totalCount;
}
