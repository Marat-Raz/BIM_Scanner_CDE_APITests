package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpSendTestEmailInput {

    public String senderEmailAddress;
    public String targetEmailAddress;
    public String subject;
    public String body;
}
