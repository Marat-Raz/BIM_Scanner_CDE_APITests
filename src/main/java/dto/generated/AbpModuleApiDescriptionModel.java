package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import dto.generated.AbpControllerApiDescriptionModel;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpModuleApiDescriptionModel {

    public String rootPath;
    public String remoteServiceName;
    public Map<String, AbpControllerApiDescriptionModel> controllers;
}
