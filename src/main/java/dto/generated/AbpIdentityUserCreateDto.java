package dto.generated;

import java.util.ArrayList;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpIdentityUserCreateDto {

    public String userName;
    public String name;
    public String surname;
    public String email;
    public String phoneNumber;
    public Boolean isActive;
    public Boolean lockoutEnabled;
    public ArrayList<String> roleNames;
    public String password;
}
