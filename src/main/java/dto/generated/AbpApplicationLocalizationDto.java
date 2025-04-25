package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpApplicationLocalizationDto {

    private Object resources;
    private AbpCurrentCultureDto currentCulture;
}
