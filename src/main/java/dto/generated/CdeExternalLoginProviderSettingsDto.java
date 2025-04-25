package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeExternalLoginProviderSettingsDto {

    private String id;
    private String name;
    private Boolean isEnabled;
    private Boolean shouldLogoutFromProvider;
    private String displayName;
    private String authority;
    private String clientId;
    private String clientSecret;
}
