package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import dto.generated.AbpExtensionEnumDto;
import dto.generated.AbpModuleExtensionDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpObjectExtensionsDto {

    public Map<String, AbpModuleExtensionDto> modules;
    public Map<String, AbpExtensionEnumDto> enums;
}
