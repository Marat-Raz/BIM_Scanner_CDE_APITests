package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpIdentityUserDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpIdentityUserDtoPagedResultDto {

    public ArrayList<AbpIdentityUserDto> items;
    public Long totalCount;
}
