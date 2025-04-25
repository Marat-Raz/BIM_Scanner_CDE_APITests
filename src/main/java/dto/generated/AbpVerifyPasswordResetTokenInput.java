package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpVerifyPasswordResetTokenInput {

    private String userId;
    private String resetToken;
}
