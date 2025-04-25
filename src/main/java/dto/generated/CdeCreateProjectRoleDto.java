package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeCreateProjectRoleDto {

    private String name;
    private Boolean isDefault;
    private ArrayList<String> permissions;
}
