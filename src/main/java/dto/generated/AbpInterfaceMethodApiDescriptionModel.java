package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpInterfaceMethodApiDescriptionModel {

    private String name;
    private ArrayList<AbpMethodParameterApiDescriptionModel> parametersOnMethod;
    private AbpReturnValueApiDescriptionModel returnValue;
}
