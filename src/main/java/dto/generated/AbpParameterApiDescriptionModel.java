package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpParameterApiDescriptionModel {

    public String nameOnMethod;
    public String name;
    public String jsonName;
    public String type;
    public String typeSimple;
    public Boolean isOptional;
    public Object defaultValue;
    public ArrayList<String> constraintTypes;
    public String bindingSourceId;
    public String descriptorName;
}
