package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.AbpDateTimeFormatDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpCurrentCultureDto {

    public String displayName;
    public String englishName;
    public String threeLetterIsoLanguageName;
    public String twoLetterIsoLanguageName;
    public Boolean isRightToLeft;
    public String cultureName;
    public String name;
    public String nativeName;
    public AbpDateTimeFormatDto dateTimeFormat;
}
