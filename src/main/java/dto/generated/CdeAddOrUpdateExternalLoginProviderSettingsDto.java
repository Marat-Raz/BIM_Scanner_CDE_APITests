package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeAddOrUpdateExternalLoginProviderSettingsDto {

    public String name;
    public Boolean isEnabled;
    public Boolean shouldLogoutFromProvider;
    public String displayName;
    public String authority;
    public String clientId;
    public String clientSecret;
}
