package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpTypeApiDescriptionModel {

    private String baseType;
    private Boolean isEnum;
    private ArrayList<String> enumNames;
    private ArrayList<Object> enumValues;
    private ArrayList<String> genericArguments;
    private ArrayList<AbpPropertyApiDescriptionModel> properties;
}
