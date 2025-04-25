package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpUserData {

    private String id;
    private String tenantId;
    private String userName;
    private String name;
    private String surname;
    private Boolean isActive;
    private String email;
    private Boolean emailConfirmed;
    private String phoneNumber;
    private Boolean phoneNumberConfirmed;
    private Object extraProperties;
}
