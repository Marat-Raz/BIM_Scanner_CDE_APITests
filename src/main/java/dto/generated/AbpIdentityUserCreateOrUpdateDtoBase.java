package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpIdentityUserCreateOrUpdateDtoBase {

    private Object extraProperties;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private Boolean lockoutEnabled;
    private ArrayList<String> roleNames;
}
