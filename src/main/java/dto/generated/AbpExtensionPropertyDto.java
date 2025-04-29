package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Map;
import dto.generated.AbpLocalizableStringDto;
import dto.generated.AbpExtensionPropertyUiDto;
import dto.generated.AbpExtensionPropertyAttributeDto;
import dto.generated.AbpExtensionPropertyApiDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpExtensionPropertyDto {

    public String type;
    public String typeSimple;
    public AbpLocalizableStringDto displayName;
    public AbpExtensionPropertyApiDto api;
    public AbpExtensionPropertyUiDto ui;
    public ArrayList<AbpExtensionPropertyAttributeDto> attributes;
    public Map<String, Object> configuration;
    public Object defaultValue;
}
