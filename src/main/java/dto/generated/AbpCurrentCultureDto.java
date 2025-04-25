package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpCurrentCultureDto {

    private String displayName;
    private String englishName;
    private String threeLetterIsoLanguageName;
    private String twoLetterIsoLanguageName;
    private Boolean isRightToLeft;
    private String cultureName;
    private String name;
    private String nativeName;
    private AbpDateTimeFormatDto dateTimeFormat;
}
