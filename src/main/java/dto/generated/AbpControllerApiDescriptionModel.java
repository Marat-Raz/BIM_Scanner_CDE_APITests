package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpControllerApiDescriptionModel {

    private String controllerName;
    private String controllerGroupName;
    private Boolean isRemoteService;
    private Boolean isIntegrationService;
    private String apiVersion;
    private String type;
    private ArrayList<AbpControllerInterfaceApiDescriptionModel> interfaces;
    private Object actions;
}
