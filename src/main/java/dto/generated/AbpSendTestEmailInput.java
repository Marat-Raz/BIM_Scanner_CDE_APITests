package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpSendTestEmailInput {

    private String senderEmailAddress;
    private String targetEmailAddress;
    private String subject;
    private String body;
}
