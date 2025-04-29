package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Map;
import dto.generated.AbpControllerInterfaceApiDescriptionModel;
import dto.generated.AbpActionApiDescriptionModel;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpControllerApiDescriptionModel {

    public String controllerName;
    public String controllerGroupName;
    public Boolean isRemoteService;
    public Boolean isIntegrationService;
    public String apiVersion;
    public String type;
    public ArrayList<AbpControllerInterfaceApiDescriptionModel> interfaces;
    public Map<String, AbpActionApiDescriptionModel> actions;
}
