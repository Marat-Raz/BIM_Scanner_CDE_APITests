package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpIdentityUserDtoPagedResultDto {

    private ArrayList<AbpIdentityUserDto> items;
    private Long totalCount;
}
