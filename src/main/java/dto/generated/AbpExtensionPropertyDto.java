package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpExtensionPropertyDto {

    private String type;
    private String typeSimple;
    private AbpLocalizableStringDto displayName;
    private AbpExtensionPropertyApiDto api;
    private AbpExtensionPropertyUiDto ui;
    private ArrayList<AbpExtensionPropertyAttributeDto> attributes;
    private Object configuration;
    private Object defaultValue;
}
