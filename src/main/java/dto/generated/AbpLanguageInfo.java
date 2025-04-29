package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpLanguageInfo {

    public String cultureName;
    public String uiCultureName;
    public String displayName;
    public String twoLetterISOLanguageName;
}
