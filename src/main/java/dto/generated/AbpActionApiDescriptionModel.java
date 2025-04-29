package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpMethodParameterApiDescriptionModel;
import dto.generated.AbpReturnValueApiDescriptionModel;
import dto.generated.AbpParameterApiDescriptionModel;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpActionApiDescriptionModel {

    public String uniqueName;
    public String name;
    public String httpMethod;
    public String url;
    public ArrayList<String> supportedVersions;
    public ArrayList<AbpMethodParameterApiDescriptionModel> parametersOnMethod;
    public ArrayList<AbpParameterApiDescriptionModel> parameters;
    public AbpReturnValueApiDescriptionModel returnValue;
    public Boolean allowAnonymous;
    public String implementFrom;
}
