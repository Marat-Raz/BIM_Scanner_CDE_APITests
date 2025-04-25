package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpActionApiDescriptionModel {

    private String uniqueName;
    private String name;
    private String httpMethod;
    private String url;
    private ArrayList<String> supportedVersions;
    private ArrayList<AbpMethodParameterApiDescriptionModel> parametersOnMethod;
    private ArrayList<AbpParameterApiDescriptionModel> parameters;
    private AbpReturnValueApiDescriptionModel returnValue;
    private Boolean allowAnonymous;
    private String implementFrom;
}
