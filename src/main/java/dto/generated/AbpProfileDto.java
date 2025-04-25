package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpProfileDto {

    private Object extraProperties;
    private String userName;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private Boolean isExternal;
    private Boolean hasPassword;
    private String concurrencyStamp;
}
