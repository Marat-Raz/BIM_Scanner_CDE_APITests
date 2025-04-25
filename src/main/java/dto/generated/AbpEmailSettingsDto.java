package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpEmailSettingsDto {

    private String smtpHost;
    private Integer smtpPort;
    private String smtpUserName;
    private String smtpPassword;
    private String smtpDomain;
    private Boolean smtpEnableSsl;
    private Boolean smtpUseDefaultCredentials;
    private String defaultFromAddress;
    private String defaultFromDisplayName;
}
