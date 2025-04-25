package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpParameterApiDescriptionModel {

    private String nameOnMethod;
    private String name;
    private String jsonName;
    private String type;
    private String typeSimple;
    private Boolean isOptional;
    private Object defaultValue;
    private ArrayList<String> constraintTypes;
    private String bindingSourceId;
    private String descriptorName;
}
