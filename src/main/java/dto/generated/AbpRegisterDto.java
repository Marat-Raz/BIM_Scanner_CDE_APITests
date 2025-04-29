package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpRegisterDto {

    public String userName;
    public String emailAddress;
    public String password;
    public String appName;
}
