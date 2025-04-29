package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import dto.generated.AbpExtensionPropertyDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpEntityExtensionDto {

    public Map<String, AbpExtensionPropertyDto> properties;
    public Map<String, Object> configuration;
}
