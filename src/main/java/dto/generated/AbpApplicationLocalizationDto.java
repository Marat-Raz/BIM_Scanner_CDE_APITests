package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import dto.generated.AbpApplicationLocalizationResourceDto;
import dto.generated.AbpCurrentCultureDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpApplicationLocalizationDto {

    public Map<String, AbpApplicationLocalizationResourceDto> resources;
    public AbpCurrentCultureDto currentCulture;
}
