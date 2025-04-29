package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Map;
import dto.generated.AbpLanguageInfo;
import dto.generated.AbpApplicationLocalizationResourceDto;
import dto.generated.AbpNameValue;
import dto.generated.AbpCurrentCultureDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpApplicationLocalizationConfigurationDto {

    public Map<String, Map<String, String>> values;
    public Map<String, AbpApplicationLocalizationResourceDto> resources;
    public ArrayList<AbpLanguageInfo> languages;
    public AbpCurrentCultureDto currentCulture;
    public String defaultResourceName;
    public Map<String, ArrayList<AbpNameValue>> languagesMap;
    public Map<String, ArrayList<AbpNameValue>> languageFilesMap;
}
