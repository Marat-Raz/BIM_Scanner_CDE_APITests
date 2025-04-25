package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpResetPasswordDto {

    private String userId;
    private String resetToken;
    private String password;
}
