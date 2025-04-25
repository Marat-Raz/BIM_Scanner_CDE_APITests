package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpTenantDtoPagedResultDto {

    private ArrayList<AbpTenantDto> items;
    private Long totalCount;
}
