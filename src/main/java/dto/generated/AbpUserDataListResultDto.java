package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpUserData;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpUserDataListResultDto {

    public ArrayList<AbpUserData> items;
}
