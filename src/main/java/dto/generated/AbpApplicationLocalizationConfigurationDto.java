package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpApplicationLocalizationConfigurationDto {

    private Object values;
    private Object resources;
    private ArrayList<AbpLanguageInfo> languages;
    private AbpCurrentCultureDto currentCulture;
    private String defaultResourceName;
    private Object languagesMap;
    private Object languageFilesMap;
}
