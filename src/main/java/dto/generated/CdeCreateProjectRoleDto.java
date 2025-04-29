package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeCreateProjectRoleDto {

    public String name;
    public Boolean isDefault;
    public ArrayList<String> permissions;
}
