package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeProjectDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeProjectDtoPagedResultDto {

    public ArrayList<CdeProjectDto> items;
    public Long totalCount;
}
