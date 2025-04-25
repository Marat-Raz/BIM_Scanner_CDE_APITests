package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpSendPasswordResetCodeDto {

    private String email;
    private String appName;
    private String returnUrl;
    private String returnUrlHash;
}
