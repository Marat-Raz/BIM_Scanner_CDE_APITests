package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpSendPasswordResetCodeDto {

    public String email;
    public String appName;
    public String returnUrl;
    public String returnUrlHash;
}
