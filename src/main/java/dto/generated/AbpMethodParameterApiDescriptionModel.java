package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpMethodParameterApiDescriptionModel {

    private String name;
    private String typeAsString;
    private String type;
    private String typeSimple;
    private Boolean isOptional;
    private Object defaultValue;
}
