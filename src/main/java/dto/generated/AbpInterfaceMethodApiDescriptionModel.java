package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpMethodParameterApiDescriptionModel;
import dto.generated.AbpReturnValueApiDescriptionModel;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpInterfaceMethodApiDescriptionModel {

    public String name;
    public ArrayList<AbpMethodParameterApiDescriptionModel> parametersOnMethod;
    public AbpReturnValueApiDescriptionModel returnValue;
}
