package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpUserData {

    public String id;
    public String tenantId;
    public String userName;
    public String name;
    public String surname;
    public Boolean isActive;
    public String email;
    public Boolean emailConfirmed;
    public String phoneNumber;
    public Boolean phoneNumberConfirmed;
}
