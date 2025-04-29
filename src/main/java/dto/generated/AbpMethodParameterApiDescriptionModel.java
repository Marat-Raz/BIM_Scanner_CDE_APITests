package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpMethodParameterApiDescriptionModel {

    public String name;
    public String typeAsString;
    public String type;
    public String typeSimple;
    public Boolean isOptional;
    public Object defaultValue;
}
