package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpPropertyApiDescriptionModel;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpTypeApiDescriptionModel {

    public String baseType;
    public Boolean isEnum;
    public ArrayList<String> enumNames;
    public ArrayList<Object> enumValues;
    public ArrayList<String> genericArguments;
    public ArrayList<AbpPropertyApiDescriptionModel> properties;
}
