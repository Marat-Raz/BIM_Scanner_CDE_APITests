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
