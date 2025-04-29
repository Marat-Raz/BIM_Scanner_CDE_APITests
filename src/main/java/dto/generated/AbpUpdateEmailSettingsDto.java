package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpUpdateEmailSettingsDto {

    public String smtpHost;
    public Integer smtpPort;
    public String smtpUserName;
    public String smtpPassword;
    public String smtpDomain;
    public Boolean smtpEnableSsl;
    public Boolean smtpUseDefaultCredentials;
    public String defaultFromAddress;
    public String defaultFromDisplayName;
}
