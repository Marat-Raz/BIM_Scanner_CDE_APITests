package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import dto.generated.AbpEntityExtensionDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpModuleExtensionDto {

    public Map<String, AbpEntityExtensionDto> entities;
    public Map<String, Object> configuration;
}
